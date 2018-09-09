package com.hewei.longflowtest.flow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class StepActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 没有从正常流程进入该Activity
        if (FlowPresenter.getInstance() == null) {
            finish();
            // ToDo goto main
        }
    }
}
