package com.hewei.longflowtest.flow;

import android.app.Activity;
import android.content.Intent;

import com.hewei.longflowtest.flow.viewmodel.AgeViewModel;
import com.hewei.longflowtest.flow.viewmodel.NameViewModel;

public class FlowPresenter {
    private int mStep = 0;

    private FlowPresenter() {

    }

    private static FlowPresenter sInst;

    public static FlowPresenter getInstance() {
        if (sInst == null) {
            sInst = new FlowPresenter();
        }
        return sInst;
    }

    private NameViewModel nameViewModel;
    private AgeViewModel ageViewModel;

    public NameViewModel getNameViewModel() {
        return nameViewModel;
    }

    public AgeViewModel getAgeViewModel() {
        return ageViewModel;
    }


    public void step(Activity activity, int step) {
        mStep = step;
        if (mStep == 1) {
            if (nameViewModel == null) {
                nameViewModel = new NameViewModel();
            }

            Intent intent = new Intent (activity, NameActivity.class);
            activity.startActivity(intent);
        } else if (mStep == 2) {
            if (ageViewModel == null) {
                ageViewModel = new AgeViewModel();
            }
            Intent intent = new Intent (activity, Step2Activity.class);
            activity.startActivity(intent);
        } else if (mStep == 3) {
            Intent intent = new Intent (activity, DetailsActivity.class);
            intent.putExtra("details", "name: " + nameViewModel.getName().getValue()
                + ", age = " + ageViewModel.getAge().getValue());

            activity.startActivity(intent);
        }
    }
}
