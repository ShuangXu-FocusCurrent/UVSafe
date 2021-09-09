package com.e.uvsafeaustralia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideViewPagerAdapter extends PagerAdapter {
    Context context;

    public SlideViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_slide_pages, container, false);

        ImageView introInfo1 = view.findViewById(R.id.imageViewIntroInfo1);
        ImageView sliderRadio1 = view.findViewById(R.id.sliderRadio1);
        ImageView sliderRadio2 = view.findViewById(R.id.sliderRadio2);
        ImageView sliderRadio3 = view.findViewById(R.id.sliderRadio3);
        ImageView sliderRadio4 = view.findViewById(R.id.sliderRadio4);
        ImageView sliderRadio5 = view.findViewById(R.id.sliderRadio5);
        ImageView nextBtn = view.findViewById(R.id.imageViewNextIcn2);
        ImageView backBtn = view.findViewById(R.id.imageViewBackIcn1);
        Button getStartedBtn = view.findViewById(R.id.getStartedBtn);

        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,MainFunction.class);
                intent.putExtra("slide","slide");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideActivity.viewPager.setCurrentItem(position+1);

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideActivity.viewPager.setCurrentItem(position-1);
            }
        });


        switch(position){
            case 0:
                introInfo1.setImageResource(R.drawable.slider1);
                sliderRadio1.setImageResource(R.drawable.ic_slider_selected);
                sliderRadio2.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio3.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio4.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio5.setImageResource(R.drawable.ic_slider_not_selected);
                backBtn.setVisibility(View.GONE);
                nextBtn.setVisibility(View.VISIBLE);
                break;

            case 1:
                introInfo1.setImageResource(R.drawable.slider2);
                sliderRadio1.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio2.setImageResource(R.drawable.ic_slider_selected);
                sliderRadio3.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio4.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio5.setImageResource(R.drawable.ic_slider_not_selected);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
                break;

            case 2:
                introInfo1.setImageResource(R.drawable.slider3);
                sliderRadio1.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio2.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio3.setImageResource(R.drawable.ic_slider_selected);
                sliderRadio4.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio5.setImageResource(R.drawable.ic_slider_not_selected);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
                break;

            case 3:
                introInfo1.setImageResource(R.drawable.slider4);
                sliderRadio1.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio2.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio3.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio4.setImageResource(R.drawable.ic_slider_selected);
                sliderRadio5.setImageResource(R.drawable.ic_slider_not_selected);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
                break;

            case 4:
                introInfo1.setImageResource(R.drawable.slider5);
                sliderRadio1.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio2.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio3.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio4.setImageResource(R.drawable.ic_slider_not_selected);
                sliderRadio5.setImageResource(R.drawable.ic_slider_selected);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.GONE);
                break;
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}




















