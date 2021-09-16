package com.e.uvsafeaustralia.fragment;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.e.uvsafeaustralia.AlarmReceiver;
import com.e.uvsafeaustralia.NotificationHelper;
import com.e.uvsafeaustralia.NotificationScheduler;
import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.SharedViewModel;
import com.e.uvsafeaustralia.UtilTools;
import com.e.uvsafeaustralia.databinding.FragmentAlarmPageBinding;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.


 * create an instance of this fragment.
 */
public class AlarmPageFragment extends Fragment {
    private FragmentAlarmPageBinding binding;
    private Switch switchNotification;
    private Switch switchAlarm;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private SharedViewModel sharedViewModel;
    private long addhours;
    final Handler handler = new Handler(Looper.getMainLooper());

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAlarmPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getContext(),R.array.hours_selection,R.layout.custon_spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.timeSlection.setAdapter(adapter);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        switchAlarm = (Switch) root.findViewById(R.id.switchAlarm);

        if (getActivity().getPreferences(Context.MODE_PRIVATE).contains("alarmState")) {
            Boolean test = sp.getBoolean("alarmState", false);
            switchAlarm.setChecked(sp.getBoolean("alarmState", false));
        }else {
            setAlarmState(false);
            switchAlarm.setChecked(false);
        }

        if (getActivity().getPreferences(Context.MODE_PRIVATE).contains("startTime")&& getActivity().getPreferences(Context.MODE_PRIVATE).contains("addHours")){
           long startTimeValue =sp.getLong("startTime",1);
           long addhoursValue =sp.getLong("addHours",1);
           long currentTime = System.currentTimeMillis();
           if(startTimeValue+addhoursValue<= currentTime){
               binding.switchAlarm.setChecked(false);
               sharedViewModel.setSwitchTag("true");
            }
        }

        binding.switchAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    setAlarmState(true);
                    String text =binding.timeSlection.getSelectedItem().toString().trim();
                    String m = text.substring(2,3);
                    long current = System.currentTimeMillis();
                    if(text.substring(2,3).equals("m")){
                        int value = Integer.valueOf(text.substring(0,1));
                        addhours = 1000*60*value;
                    }else{
                        int value = Integer.valueOf(text.substring(0,1));
                        addhours = 1000*60*60*value;
                    }
                    startAlarm(addhours);
                    setAlarmState(true);
                    storeTime(current,addhours);
                    sharedViewModel.setSwitchTag("True");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setAlarmState(false);
                            sharedViewModel.setSwitchTag("False");
                        }
                    },addhours+2);

              }else{
                    cancelAlarm();
                    setAlarmState(false);
                    handler.removeCallbacksAndMessages(null);
                }
            }
        });

        sharedViewModel.getSwitchTag().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equals("False")){
                    binding.switchAlarm.setChecked(false);
                }
            }
        });

        switchNotification = (Switch) root.findViewById(R.id.switchNotification);
        if (getActivity().getPreferences(Context.MODE_PRIVATE).contains("notificationState"))
            switchNotification.setChecked(sp.getBoolean("notificationState", false));
        else {
            setNotificationState(false);
            switchNotification.setChecked(false);
        }
        switchNotification.setOnClickListener(new SwitchNotificationClick());
        return root;
    }

    private PeriodicWorkRequest checkWeather;
    private class SwitchNotificationClick implements View.OnClickListener {
        private String suburb = sp.getString("suburb", UtilTools.DEFAULT_SUBURB);
        private String postcode = sp.getString("postcode", UtilTools.DEFAULT_POSTCODE);
        private String latitude = sp.getString("latitude", UtilTools.DEFAULT_LATITUDE);
        private String longitude = sp.getString("longitude", UtilTools.DEFAULT_LONGITUDE);
        @Override
        public void onClick(View v) {
            Data locationData = new Data.Builder()
                    .putString("suburb", suburb)
                    .putString("postcode", postcode)
                    .putString("latitude", latitude)
                    .putString("longitude", longitude)
                    .build();

            Constraints constraint = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();

            // For Development/testing, send notification every 15 minutes.
            // For production, change 15 minutes to 1 hour!!
            checkWeather = new PeriodicWorkRequest.Builder(NotificationScheduler.class, 15, TimeUnit.MINUTES)
                    .setInputData(locationData)
                    .addTag("checkUV")
                    .setConstraints(constraint)
                    .setInitialDelay(15, TimeUnit.MINUTES)
                    .build();

            if (switchNotification.isChecked()) {
                Toast.makeText(getActivity(), switchNotification.getTextOn().toString(), Toast.LENGTH_LONG).show();
                setNotificationState(true);
                WorkManager.getInstance(requireActivity()).enqueueUniquePeriodicWork("checkUV", ExistingPeriodicWorkPolicy.REPLACE, checkWeather);
            }
            else {
                Toast.makeText(getActivity(), switchNotification.getTextOff().toString(), Toast.LENGTH_LONG).show();
                setNotificationState(false);
                WorkManager.getInstance(requireActivity())
                        .cancelAllWorkByTag("checkUV");
            }
        }
    }

    private void setNotificationState(Boolean state) {
        editor = sp.edit();
        editor.putBoolean ("notificationState", state);
        editor.commit();
    }


    private void setAlarmState(Boolean state) {
        editor = sp.edit();
        editor.putBoolean ("alarmState", state);
        editor.commit();
    }

    private void storeTime(long currentTime, long addTime) {
        editor = sp.edit();
        editor.putLong ("startTime", currentTime);
        editor.putLong ("addHours", addTime);
        editor.commit();
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void startAlarm(long addhours){
        long currentTime = System.currentTimeMillis();
        Toast.makeText(getActivity(), "We will remind you to reapply sunscreen", Toast.LENGTH_LONG).show();
        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(requireActivity(), AlarmReceiver.class);
        intent.putExtra("notification","alarmPageFragment");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(requireActivity(),1,intent,0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,currentTime+addhours,pendingIntent);
    }

    private void cancelAlarm(){
        Toast.makeText(getActivity(), switchAlarm.getTextOff().toString(), Toast.LENGTH_LONG).show();
        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(requireActivity(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(requireActivity(),1,intent,0);
        alarmManager.cancel(pendingIntent);
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}
