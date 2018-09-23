package com.asmat.rolando.popularmovies.activities;


import com.asmat.rolando.popularmovies.R;
import com.asmat.rolando.popularmovies.adapters.BaseSectionsPagerAdapter;
import com.asmat.rolando.popularmovies.adapters.MyListsPagerAdapter;

public class MyListsActivity extends BaseActivity {

    @Override
    String getActivityTitle() {
        return getString(R.string.my_lists);
    }

    @Override
    BaseSectionsPagerAdapter getPagerAdapter() {
        return new MyListsPagerAdapter(getSupportFragmentManager(),this);
    }
}
