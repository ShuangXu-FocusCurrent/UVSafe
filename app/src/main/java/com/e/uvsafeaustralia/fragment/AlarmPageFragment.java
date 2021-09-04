package com.e.uvsafeaustralia.fragment;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Switch;
import android.widget.Toast;

import com.e.uvsafeaustralia.LocationActivity;
import com.e.uvsafeaustralia.ProtectionActivity;

import com.e.uvsafeaustralia.R;

import com.e.uvsafeaustralia.databinding.FragmentAlarmPageBinding;
import com.e.uvsafeaustralia.databinding.FragmentHomePageBinding;


import static com.e.uvsafeaustralia.App.CHANNEL_1_ID;


/**
 * A simple {@link Fragment} subclass.


 * create an instance of this fragment.
 */
public class AlarmPageFragment extends Fragment {


    private FragmentAlarmPageBinding binding;

    Switch switchNotification;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAlarmPageBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        switchNotification = (Switch) root.findViewById(R.id.switchNotification);
        switchNotification.setOnClickListener(new SwitchNotificationClick());

        return root;

    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }

    private class SwitchNotificationClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (switchNotification.isChecked()) {
                Toast.makeText(getActivity(), switchNotification.getTextOn().toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ProtectionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                Notification builder = new NotificationCompat.Builder(getActivity(), CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle("UVSafeAustralia")
                        .setContentText("Sun protection is now required.")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Sun protection is now required. See what protection measures you can take."))
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .build();



                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());
                notificationManager.notify(1, builder);

            }
            else
                Toast.makeText(getActivity(), switchNotification.getTextOff().toString(), Toast.LENGTH_LONG).show();
        }
    }

}
