package com.hewei.longflowtest.flow.viewmodel;

import android.arch.lifecycle.ViewModel;

public class FlowViewModel extends ViewModel {
    public String name;
    public int age;

    @Override
    public String toString() {
        return "name: " + name + ", age = " + age;
    }
}
