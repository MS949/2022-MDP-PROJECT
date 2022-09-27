package com.headthings.mdp_project_2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.headthings.mdp_project_2022.TransForm.ZoomOutPageTransformer;
import com.headthings.mdp_project_2022.mainFragment.HomeFragment;
import com.headthings.mdp_project_2022.mainFragment.ListFragment;
import com.headthings.mdp_project_2022.mainFragment.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.com.st235.lib_expandablebottombar.ExpandableBottomBar;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_bar)
    ExpandableBottomBar bottomBar;

    @BindView(R.id.main_pager)
    ViewPager2 mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(this);

        mPager.setAdapter(pagerAdapter);
        mPager.setPageTransformer(new ZoomOutPageTransformer());
        mPager.setUserInputEnabled(false);

        bottomBar.setOnItemSelectedListener((view, menuItem, aBoolean) -> {
            switch (menuItem.getId()) {
                case R.id.home:
                    mPager.setCurrentItem(0);
                    break;
                case R.id.list:
                    mPager.setCurrentItem(1);
                    break;
                case R.id.setting:
                    mPager.setCurrentItem(2);
                    break;
            }

            return null;
        });
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

        private static final int NUM_PAGES = 3;

        public ScreenSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new ListFragment();
                case 2:
                    return new SettingFragment();
            }
            return new HomeFragment();
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}
