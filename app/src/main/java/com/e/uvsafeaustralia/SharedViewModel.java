package com.e.uvsafeaustralia;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<LocationModel> location;

    public SharedViewModel(){
        location = new MutableLiveData<>();
    }
    public void setLocation(LocationModel message) {
        location.setValue(message);
    }
    public LiveData<LocationModel> getLocation() {
        return location; }


}
