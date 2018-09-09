package com.hewei.longflowtest.flow;

import android.app.Activity;
import android.content.Intent;

import com.hewei.longflowtest.flow.viewmodel.FlowViewModel;

public class FlowPresenter {
    private int mStep = 0;
    private FlowViewModel flowViewModel = new FlowViewModel();

    private FlowPresenter() {

    }

    private static FlowPresenter sInst;

    public static void init() {
        if (sInst == null) {
            sInst = new FlowPresenter();
        }
    }

    public static void destroy() {
        sInst = null;
    }

    public static FlowPresenter getInstance() {
        return sInst;
    }

    public void setName(String name) {
        flowViewModel.name = name;
    }

    public void setAge(int age) {
        flowViewModel.age = age;
    }

    public void step(Activity activity, int step) {
        mStep = step;
        if (mStep == 1) {
            Intent intent = new Intent (activity, NameActivity.class);
            activity.startActivity(intent);
        } else if (mStep == 2) {
            Intent intent = new Intent (activity, AgeActivity.class);
            activity.startActivity(intent);
        } else if (mStep == 3) {
            Intent intent = new Intent (activity, DetailsActivity.class);
            intent.putExtra("details", flowViewModel.toString());

            activity.startActivity(intent);
        }
    }
}
