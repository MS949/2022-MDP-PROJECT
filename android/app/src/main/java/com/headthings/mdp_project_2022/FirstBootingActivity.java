package com.headthings.mdp_project_2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.headthings.mdp_project_2022.TransForm.DepthPageTransformer;
import com.headthings.mdp_project_2022.initPageFragment.InitPageFourFragment;
import com.headthings.mdp_project_2022.initPageFragment.InitPageOneFragment;
import com.headthings.mdp_project_2022.initPageFragment.InitPageThreeFragment;
import com.headthings.mdp_project_2022.initPageFragment.InitPageTwoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator3;

public class FirstBootingActivity extends FragmentActivity {

    @BindView(R.id.indicator)
    CircleIndicator3 indicator;

    @BindView(R.id.init_pager)
    ViewPager2 mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_booting);
        ButterKnife.bind(this, this);

        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(this);

        mPager.setAdapter(pagerAdapter);
        mPager.setPageTransformer(new DepthPageTransformer());

        indicator.setViewPager(mPager);
    }

    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter {

        private static final int NUM_PAGES = 4;

        public ScreenSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new InitPageOneFragment();
                case 1:
                    return new InitPageTwoFragment();
                case 2:
                    return new InitPageThreeFragment();
                case 3:
                    return new InitPageFourFragment();
            }
            return new InitPageOneFragment();
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }

    }
}