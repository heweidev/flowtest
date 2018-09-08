package com.hewei.longflowtest.flow;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.hewei.longflowtest.Constants;
import com.hewei.longflowtest.R;
import com.hewei.longflowtest.common.LocalObject;
import com.hewei.longflowtest.flow.viewmodel.NameViewModel;

public class NameActivity extends AppCompatActivity {
    private NameViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        /*
        LocalObject<NameViewModel> obj = getIntent().getParcelableExtra(Constants.EXTRA_VIEW_MODEL);
        if (obj != null) {
            mViewModel = obj.get();
        }

        if (mViewModel == null) {
            finish();
        }
        */

        mViewModel = FlowPresenter.getInstance().getNameViewModel();

        final TextView textView = findViewById(R.id.name);
        mViewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        EditText editText = findViewById(R.id.etName);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mViewModel.saveName(v.getText().toString());
                    return true;
                }

                return false;
            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowPresenter.getInstance().step(NameActivity.this, 2);
            }
        });
    }
}
