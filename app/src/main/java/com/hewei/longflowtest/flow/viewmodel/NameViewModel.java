package com.hewei.longflowtest.flow.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;


public class NameViewModel extends ViewModel {
    MutableLiveData<String> name = new MutableLiveData<>();

    public LiveData<String> getName() {
        return name;
    }

    public void saveName(String data) {
        name.postValue(data);
    }
}
