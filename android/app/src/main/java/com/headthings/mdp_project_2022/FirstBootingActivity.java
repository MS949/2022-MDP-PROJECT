package com.headthings.mdp_project_2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.headthings.mdp_project_2022.Fragment.InitPageFragment;

import me.relex.circleindicator.CircleIndicator3;

public class FirstBootingActivity extends FragmentActivity {

    private static final int NUM_PAGES = 5;

    private ViewPager2 mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_booting);

        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(this);

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(pagerAdapter);

        mPager.setPageTransformer(new DepthPageTransformer());

        CircleIndicator3 indicator = findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter {

        public ScreenSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 1:
                    return new InitPageFragment();
                case 2:
                    return new InitPageFragment();
                case 3:
                    return new InitPageFragment();
                case 4:
                    return new InitPageFragment();
                case 5:
                    return new InitPageFragment();
            }
            return new InitPageFragment();
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}