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
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private int hour;
    private int minute;
    private Calendar calendar;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAlarmPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sp = getActivity().getPreferences(Context.MODE_PRIVATE);

        switchAlarm = (Switch) root.findViewById(R.id.switchAlarm);
        if (getActivity().getPreferences(Context.MODE_PRIVATE).contains("alarmState"))
            switchAlarm.setChecked(sp.getBoolean("alarmState", false));
        else {
            setAlarmState(false);
            switchAlarm.setChecked(false);
        }
        binding.switchAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    PopTimePicker(root);
                    setAlarmState(true);
              }else{
                    cancelAlarm();
                    setAlarmState(false);
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

    public void PopTimePicker(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfhour) {
                hour=hourOfDay;
                minute=minuteOfhour;
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,hour);
                calendar.set(Calendar.MINUTE,minute);
                calendar.set(Calendar.SECOND,0);
                startAlarm(calendar);
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(),onTimeSetListener,hour,minute,true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void startAlarm(Calendar c){
        String timeText = "Alarm set for reapplying sunblock: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        Toast.makeText(getActivity(), timeText, Toast.LENGTH_LONG).show();
        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(requireActivity(), AlarmReceiver.class);
        intent.putExtra("notification","alarmPageFragment");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(requireActivity(),1,intent,0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
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
