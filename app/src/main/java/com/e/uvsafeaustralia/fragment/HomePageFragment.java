package com.e.uvsafeaustralia.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.e.uvsafeaustralia.LocationActivity;
import com.e.uvsafeaustralia.LocationAdapter;
import com.e.uvsafeaustralia.LocationModel;
import com.e.uvsafeaustralia.ProtectionActivity;
import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.SharedViewModel;
import com.e.uvsafeaustralia.UtilTools;
import com.e.uvsafeaustralia.databinding.FragmentHomePageBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;


public class HomePageFragment extends Fragment {
    private FragmentHomePageBinding binding;
    private SharedViewModel sharedViewModel;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sp.edit();
        if (!sp.contains("suburb")) {
            // set default location to Melbourne
            editor.putString("suburb", UtilTools.DEFAULT_SUBURB);
            editor.putString("postcode", UtilTools.DEFAULT_POSTCODE);
            editor.putString("latitude", UtilTools.DEFAULT_LATITUDE);
            editor.putString("longitude", UtilTools.DEFAULT_LONGITUDE);
            editor.commit();
        }

        sharedViewModel.getLocation().observe(getViewLifecycleOwner(), new Observer<LocationModel>() {
            @Override
            public void onChanged(LocationModel locationModel) {
                String suburb = locationModel.getSuburb();
                binding.address.setText(suburb);
                editor.putString("suburb", locationModel.getSuburb());
                editor.putString("postcode", locationModel.getPostcode());
                editor.putString("latitude", locationModel.getLatitude());
                editor.putString("longitude", locationModel.getLongitude());
                editor.commit();
            }
        });

        sharedViewModel.getTemperature().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.temperature.setText(s.trim());
                //Log.e("Weather tResponse",s);
            }
        });

        sharedViewModel.getUvlValue().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.uvIndex.setText(s);
                int sInt = Integer.parseInt(s.trim());
//                sInt = 0;
//                 sInt = 6;
//                sInt = 8;
//                sInt = 11;
                binding.uvIndex.setText(String.valueOf(sInt));
                if(sInt>=0 && sInt<3){
                    binding.uvmeter.setImageResource(R.drawable.uv_low);
                    binding.uvInstrcut.setText("Low UV Level");
                    binding.uvInstrcut2.setText(R.string.protectNotRequired);
                }if(sInt>=3 && sInt<6){
                    binding.uvmeter.setImageResource(R.drawable.uv_moderate);
                    binding.uvInstrcut.setText("Moderate UV Level");
                    binding.uvInstrcut2.setText(R.string.protectRequired);
                }if(sInt>=6 && sInt<8){
                    binding.uvmeter.setImageResource(R.drawable.uv_high);
                    binding.uvInstrcut.setText("High UV Level");
                    binding.uvInstrcut2.setText(R.string.protectRequired);
                }if(sInt>=8 && sInt<11){
                    binding.uvmeter.setImageResource(R.drawable.uv_very_high);
                    binding.uvInstrcut.setText("Very High UV Level");
                    binding.uvInstrcut2.setText(R.string.protectRequired);
                }if(sInt>=11 ){
                    binding.uvmeter.setImageResource(R.drawable.uv_extreme);
                    binding.uvInstrcut.setText("Extreme UV Level");
                    binding.uvInstrcut2.setText(R.string.protectRequired);
                }
            }
        });

        sharedViewModel.getSunriselValue().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                int sInt = Integer.valueOf(s);
                Date days = new Date(sInt*1000L);
                SimpleDateFormat f = new SimpleDateFormat("h:mm a");
                f.setTimeZone(TimeZone.getTimeZone("Australia/Melbourne"));
                String time = f.format(days);
                binding.sunRiseTime.setText(time);
                //Log.e("Weather tResponse",s);
            }
        });

        sharedViewModel.getSunSetValue().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                int sInt = Integer.valueOf(s);
                Date days = new Date(sInt*1000L);
                SimpleDateFormat f = new SimpleDateFormat("h:mm a"); // HH for 0-23
                f.setTimeZone(TimeZone.getTimeZone("Australia/Melbourne"));
                String time = f.format(days);
                binding.sunSetTime.setText(time);
                //Log.e("Weather tResponse",s);
            }
        });


        binding.address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.address.setText("Plesase click search");
            }
        });

        binding.searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                startActivity(intent );
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        binding.buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProtectionActivity.class);
                startActivity(intent );
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}