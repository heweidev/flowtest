package com.hewei.longflowtest.flow.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;


public class AgeViewModel extends ViewModel {
    MutableLiveData<Integer> liveData = new MutableLiveData<>();

    public LiveData<Integer> getAge() {
        return liveData;
    }

    public void setAge(int age) {
        liveData.postValue(age);
    }
}
