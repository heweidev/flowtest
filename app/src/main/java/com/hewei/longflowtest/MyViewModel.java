package com.hewei.longflowtest;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

class MyViewModel extends ViewModel {
    MutableLiveData<Integer> liveIndex = new MutableLiveData<Integer>();

    public MyViewModel() {
        liveIndex.setValue(0);
    }

    public void addIndex() {
        Integer integer = liveIndex.getValue();
        liveIndex.setValue(integer + 1);
    }
}
