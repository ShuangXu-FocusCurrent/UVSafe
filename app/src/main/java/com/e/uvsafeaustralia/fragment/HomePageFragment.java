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
import com.e.uvsafeaustralia.ProtectionActivity;
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
                binding.address.setText(suburb);

            }
        });

        sharedViewModel.getTemperature().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.temperature.setText(s);
                //Log.e("Weather tResponse",s);

            }
        });

        sharedViewModel.getUvlValue().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.unIndex.setText(s);
                //Log.e("Weather uResponse",s);

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


        //getWeatherInfor(view);
        return view;


    }


    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}