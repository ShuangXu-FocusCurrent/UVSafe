package com.e.uvsafeaustralia.fragment;

import android.app.Activity;
import android.content.Intent;
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
import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.SharedViewModel;
import com.e.uvsafeaustralia.databinding.FragmentHomePageBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;


public class HomePageFragment extends Fragment {
    private FragmentHomePageBinding binding;
    private final String url = "https://api.openweathermap.org/data/2.5/onecall";
    private final String appid = "03bbeee1e357560e71cdde42465aad22";
    DecimalFormat tempdf = new DecimalFormat("#.##");
    private SharedViewModel sharedViewModel;
    private LocationAdapter locationACAdapter;
    //private String lat ;
   // private String lon;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getLocation().observe(getViewLifecycleOwner(), new Observer<LocationModel>() {
            @Override
            public void onChanged(LocationModel locationModel) {
                String suburb = locationModel.getSuburb();
                String lat = locationModel.getLatitude();
                String lon = locationModel.getLongitude();
                if(lat==null || lat == ""||lon==null || lon == ""){
                    lat="33.44";
                    lon="-94.04";

                }
                binding.address.setText(suburb);
                getWeatherInfor(view,lat,lon);
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
//                String locationText = binding.address.getText().toString();
//                binding.address.onEditorAction(EditorInfo.IME_ACTION_DONE);
//
//                //locationrv = findViewById(R.id.rvLocations);
//                binding.rvLocations.setHasFixedSize(true);
//
//                linearLayoutManager = new LinearLayoutManager(requireContext());
//                binding.rvLocations.setLayoutManager(linearLayoutManager);
//
//                locationACAdapter = new LocationAdapter(locationList, LocationActivity.this);
//                binding.rvLocations.setAdapter(locationACAdapter);



                Intent intent = new Intent(getActivity(), LocationActivity.class);
                startActivity(intent );
                ((Activity) getActivity()).overridePendingTransition(0, 0);

            }
        });


        //getWeatherInfor(view);
        return view;


    }

    public void getWeatherInfor(View view,String lat,String lon){
        String tempUrl = "";
        //lat=33.44&lon=-94.04
//        String lat = "33.44";
//        String lon = "-94.04";
        tempUrl=url+"?lat="+lat+"&lon="+lon+"&exclude=minutely,hourly,daily&appid="+appid;

        StringRequest stringRequest=new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              // Log.e("response",response);
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONObject jsonCurrent = jsonResponse.getJSONObject("current");
                    Integer dt = jsonCurrent.getInt("dt");
                    Integer sunrise = jsonCurrent.getInt("sunrise");
                    Integer sunset = jsonCurrent.getInt("sunset");
                    Double temp = jsonCurrent.getDouble("temp")-273.15;
                    Double feelsLike = jsonCurrent.getDouble("feels_like")-273.15;
                    Integer pressure = jsonCurrent.getInt("pressure");
                    Integer humidity = jsonCurrent.getInt("humidity");
                    Double dewPoint = jsonCurrent.getDouble("dew_point");
                    Double uvi = jsonCurrent.getDouble("uvi");
                    Integer clouds = jsonCurrent.getInt("clouds");
                    Double windSpeed= jsonCurrent.getDouble("wind_speed");
                    Integer windDeg= jsonCurrent.getInt("wind_deg");
                    Double windGust = jsonCurrent.getDouble("wind_gust");

                    JSONArray jsonArrayWeather = jsonCurrent.getJSONArray("weather");

                    String temperature = String.valueOf(tempdf.format(temp));

                    binding.temperature.setText(temperature);
                    binding.uvIndex.setText(String.valueOf(uvi));

                    //change protection measures according to uv index
                    if (uvi>3){
                        binding.protectionMeasures.setImageResource(R.drawable.highuvmeasures);
                    }else{
                        binding.protectionMeasures.setImageResource(R.drawable.lowuvmeasures);

                    }





                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(requireActivity(),error.toString().trim(),Toast.LENGTH_LONG).show();

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(stringRequest);

    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}