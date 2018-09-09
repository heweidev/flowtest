package com.hewei.longflowtest;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hewei.longflowtest.common.LocalObject;
import com.hewei.longflowtest.flow.FlowActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ViewPager mViewPager;
    private BottomNavigationView mNavigator;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //mNavigator.setSelectedItemId(item.getItemId());
            switchPage(item.getItemId());
            return false;
        }
    };

    private void switchPage(int tag) {
        switch (tag) {
            case R.id.navigation_home:
                //mTextMessage.setText(R.string.title_home);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.navigation_dashboard:
                //mTextMessage.setText(R.string.title_dashboard);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.navigation_notifications:
                //mTextMessage.setText(R.string.title_notifications);
                mViewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNavigator = navigation;

        ViewPager pager = findViewById(R.id.viewPager);
        mViewPager = pager;
        final MyViewModel myViewModel = new MyViewModel();
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), myViewModel));

        final TextView textView = findViewById(R.id.text);
        myViewModel.liveIndex.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                textView.setText("index = " + integer);
            }
        });

        findViewById(R.id.startFlow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowActivity.start(MainActivity.this);
            }
        });
    }

    private static class MyPagerAdapter extends FragmentPagerAdapter {
        private LocalObject<MyViewModel> model;

        public MyPagerAdapter(FragmentManager fm, MyViewModel viewModel) {
            super(fm);
            model = new LocalObject<>(viewModel);
        }

        @Override
        public Fragment getItem(int i) {
            Bundle bundle = new Bundle();
            PrimeFragment fragment = new PrimeFragment();
            bundle.putParcelable("model", model);
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
