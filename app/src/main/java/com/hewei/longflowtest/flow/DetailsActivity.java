package com.hewei.longflowtest.flow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }
}
