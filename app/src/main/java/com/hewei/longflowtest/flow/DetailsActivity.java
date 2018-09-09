package com.hewei.longflowtest.flow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hewei.longflowtest.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String details = getIntent().getStringExtra("details");
        TextView textView = findViewById(R.id.tvDetails);
        textView.setText(details);

        findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowActivity.stop(DetailsActivity.this);
            }
        });
    }
}
