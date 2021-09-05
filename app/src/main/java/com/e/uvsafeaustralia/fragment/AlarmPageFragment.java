package com.e.uvsafeaustralia.fragment;

import android.content.Context;
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
import android.widget.Switch;
import android.widget.Toast;
import com.e.uvsafeaustralia.NotificationScheduler;
import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.SharedViewModel;
import com.e.uvsafeaustralia.UtilTools;
import com.e.uvsafeaustralia.databinding.FragmentAlarmPageBinding;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.


 * create an instance of this fragment.
 */
public class AlarmPageFragment extends Fragment {

    private FragmentAlarmPageBinding binding;
//    private SharedViewModel sharedViewModel;

    private Switch switchNotification;
    private Switch switchAlarm;
    SharedPreferences sp;



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
        switchAlarm.setOnClickListener(new SwitchAlarmClick());



        switchNotification = (Switch) root.findViewById(R.id.switchNotification);
        if (getActivity().getPreferences(Context.MODE_PRIVATE).contains("notificationState"))
            switchNotification.setChecked(sp.getBoolean("state", false));
        else {
            setNotificationState(false);
            switchNotification.setChecked(false);
        }
        switchNotification.setOnClickListener(new SwitchNotificationClick());

        return root;

    }

    private PeriodicWorkRequest checkWeather;

    private class SwitchAlarmClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (switchAlarm.isChecked()) {
                Toast.makeText(getActivity(), switchAlarm.getTextOn().toString(), Toast.LENGTH_LONG).show();
                setAlarmState(true);
            }
            else {
                Toast.makeText(getActivity(), switchAlarm.getTextOff().toString(), Toast.LENGTH_LONG).show();
                setAlarmState(false);
            }
        }
    }
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
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean ("notificationState", state);
        editor.commit();
    }

    private void setAlarmState(Boolean state) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean ("alarmState", state);
        editor.commit();

    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }

}
