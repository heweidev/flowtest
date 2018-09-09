package com.hewei.longflowtest.flow;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.hewei.longflowtest.R;
import com.hewei.longflowtest.flow.viewmodel.AgeViewModel;

public class AgeActivity extends StepActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);

        final TextView tvAge = findViewById(R.id.tvAge);
        final EditText etAge = findViewById(R.id.etAge);

        final AgeViewModel viewModel = new AgeViewModel();
        viewModel.getAge().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                tvAge.setText("age = " + integer);
            }
        });

        etAge.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.setAge(Integer.parseInt(v.getText().toString()));
                    return true;
                }

                return false;
            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowPresenter.getInstance().setAge(viewModel.getAge().getValue());
                FlowPresenter.getInstance().step(AgeActivity.this, 3);
            }
        });
    }
}
