package com.e.uvsafeaustralia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {

    List<LocationModel> locationList;
    Context context;

    public LocationAdapter(List<LocationModel> locationsList, Context context) {
        this.locationList = locationsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.MyViewHolder holder, int position) {
        StringBuilder s = new StringBuilder();
        s.append(locationList.get(position).getSuburb()).append(", ").append(locationList.get(position).getPostcode()).append(", ").append(locationList.get(position).getState());

        holder.locationRowTV.setText(s);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("suburb", locationList.get(position).getSuburb());
                bundle.putString("postcode", locationList.get(position).getPostcode());
                bundle.putString("state", locationList.get(position).getState());
                bundle.putString("latitude", locationList.get(position).getLatitude());
                bundle.putString("Longitude", locationList.get(position).getLongitude());
                Intent intent = new Intent(context, MainFunction.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView locationRowTV;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            locationRowTV = itemView.findViewById(R.id.textViewLocationRow);
            parentLayout = itemView.findViewById(R.id.oneLocationLayout);
        }
    }

}
