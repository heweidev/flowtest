package com.hewei.longflowtest.flow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class FlowActivity extends Activity {
    public static void start(Activity activity) {
        Intent intent = new Intent(activity, FlowActivity.class);
        activity.startActivity(intent);
    }

    public static void stop(Activity activity) {
        FlowPresenter.destroy();

        Intent intent = new Intent(activity, FlowActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FlowPresenter.init();
        FlowPresenter.getInstance().step(this, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        finish();
    }
}
