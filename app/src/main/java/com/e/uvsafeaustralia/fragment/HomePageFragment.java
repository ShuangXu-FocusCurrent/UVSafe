package com.e.uvsafeaustralia.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.e.uvsafeaustralia.DBManager;
import com.e.uvsafeaustralia.models.LocationModel;
import com.e.uvsafeaustralia.ProtectionActivity;
import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.SharedViewModel;
import com.e.uvsafeaustralia.UtilTools;
import com.e.uvsafeaustralia.databinding.FragmentHomePageBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


public class HomePageFragment extends Fragment {
    private FragmentHomePageBinding binding;
    private SharedViewModel sharedViewModel;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private int sIntSunrise;
    private int sIntSunset;
//    private int sIntDT;
    protected DBManager dbManager;
    LocationModel locationSearched;
    ArrayList<String> locationListTrim = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sp.edit();
        binding.imageViewBoy.setVisibility(View.INVISIBLE);
        binding.buttonInfo.setVisibility(View.INVISIBLE);

        if (!sp.contains("suburb")) {
            // set default location to Melbourne
//            editor.putString("suburb", UtilTools.DEFAULT_SUBURB);
//            editor.putString("postcode", UtilTools.DEFAULT_POSTCODE);
//            editor.putString("latitude", UtilTools.DEFAULT_LATITUDE);
//            editor.putString("longitude", UtilTools.DEFAULT_LONGITUDE);
//            editor.commit();
            binding.address.setText(UtilTools.DEFAULT_SUBURB);
            sharedViewModel.getWeatherInfor(view);
        }else{
            String suburb = sp.getString("suburb", "Melbourne");
            String postcode = sp.getString("postcode", "3000");
            String latitude = sp.getString("latitude", "-37.8136");
            String longitude = sp.getString("longitude", "144.9631");
            LocationModel locationModel = new LocationModel(1,postcode, suburb, latitude, longitude);
            sharedViewModel.setLocation(locationModel);
            sharedViewModel.setLat(latitude.trim());
            sharedViewModel.setLon(longitude.trim());
            sharedViewModel.getWeatherInfor(view);
        }

        dbManager = new DBManager(requireActivity());
        String locationliststr = getLocationList();
        String[] locationListAry = locationliststr.split("\n");
        for (String location : locationListAry) {
            String[] locationListRaw = location.split(",");
            StringBuilder locationOption = new StringBuilder();
            locationOption.append(locationListRaw[0]).append(locationListRaw[1]);
            locationListTrim.add(String.valueOf(locationOption));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity(),
                android.R.layout.simple_dropdown_item_1line, locationListTrim);
        binding.address.setThreshold(1);
        binding.address.setAdapter(adapter);
        binding.address.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = String.valueOf(binding.address.getText());
                for (String item : locationListAry) {
                    String[] locationAry = item.split(",");
                    String postcode = locationAry[0];
                    String suburb = locationAry[1];
                    String latitude = locationAry[2];
                    String longitude = locationAry[3];
                    String strLoc = postcode.concat(suburb);
                    if (strLoc.equals(text)) {
                        locationSearched = new LocationModel(-1, postcode, suburb, latitude, longitude);
                        binding.address.setText(locationSearched.getSuburb());
                        sharedViewModel.setLocation(locationSearched);
                        sharedViewModel.setLat(locationSearched.getLatitude().trim());
                        sharedViewModel.setLon(locationSearched.getLongitude().trim());
                        sharedViewModel.getWeatherInfor(view);
                        editor.putString("suburb", locationSearched.getSuburb());
                        editor.putString("postcode", locationSearched.getPostcode());
                        editor.putString("latitude", locationSearched.getLatitude());
                        editor.putString("longitude", locationSearched.getLongitude());
                        editor.commit();
                        break;
                    }
                }
            }
        });


        sharedViewModel.getLocation().observe(getViewLifecycleOwner(), new Observer<LocationModel>() {
            @Override
            public void onChanged(LocationModel locationModel) {

                    binding.address.setText(locationModel.getSuburb());
            }
        });

        sharedViewModel.getTemperature().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.temperature.setText(s.trim());
                //Log.e("Weather tResponse",s);
            }
        });

        sharedViewModel.getDtValue().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Integer dt = Integer.valueOf(s);
                if (dt >= sIntSunrise && dt <= sIntSunset) {
                    binding.dayIc.setVisibility(View.VISIBLE);
                    binding.moonIc.setVisibility(View.GONE);
                }
                if (dt <= sIntSunrise || dt >= sIntSunset) {
                    binding.dayIc.setVisibility(View.GONE);
                    binding.moonIc.setVisibility(View.VISIBLE);
                }
            }
        });

        sharedViewModel.getUvlValue().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.uvIndex.setText(s);
                int sInt = Integer.parseInt(s.trim());
                // Don't remove line 102-105. They are for testing purposes.
//                sInt = 0;
//                 sInt = 6;
//                sInt = 8;
//                sInt = 11;
                binding.uvIndex.setText(String.valueOf(sInt));
                if(sInt>=0 && sInt<3){
                    binding.uvmeter.setImageResource(R.drawable.uv_low);
                    binding.buttonInfo.setVisibility(View.GONE);
                    binding.imageViewBoy.setVisibility(View.VISIBLE);
                    binding.uvInstrcut2.setText(R.string.protectNotRequired);
                }if(sInt>=3 && sInt<6){
                    binding.uvmeter.setImageResource(R.drawable.uv_moderate);
                    above3View();
                    binding.uvInstrcut2.setText(R.string.protectRequired);
                }if(sInt>=6 && sInt<8){
                    binding.uvmeter.setImageResource(R.drawable.uv_high);
                    above3View();
                }if(sInt>=8 && sInt<11){
                    binding.uvmeter.setImageResource(R.drawable.uv_very_high);
                    above3View();
                }if(sInt>=11 ){
                    binding.uvmeter.setImageResource(R.drawable.uv_extreme);
                    above3View();
                }
            }
        });

        sharedViewModel.getSunriselValue().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                sIntSunrise = Integer.valueOf(s);
                Date days = new Date(sIntSunrise*1000L);
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
                sIntSunset = Integer.valueOf(s);
                Date days = new Date(sIntSunset*1000L);
                SimpleDateFormat f = new SimpleDateFormat("h:mm a"); // HH for 0-23
                f.setTimeZone(TimeZone.getTimeZone("Australia/Melbourne"));
                String time = f.format(days);
                binding.sunSetTime.setText(time);
                //Log.e("Weather tResponse",s);
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

    public String getLocationList() {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor c = dbManager.getAllLocations();
        StringBuilder s = new StringBuilder();
        if (c.moveToFirst()) {
            do {
                s.append(c.getString(0)).append(",").append(c.getString(1)).append(",")
                        .append(c.getString(2)).append(",").append(c.getString(3)).append("\n");
            } while (c.moveToNext());
        }
        dbManager.close();
        return s.toString();
    }

    public void above3View(){
        binding.buttonInfo.setVisibility(View.VISIBLE);
        binding.imageViewBoy.setVisibility(View.GONE);
        binding.uvInstrcut2.setText(R.string.protectRequired);
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}