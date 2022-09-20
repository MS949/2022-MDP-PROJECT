package com.headthings.mdp_project_2022;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import github.com.st235.lib_expandablebottombar.ExpandableBottomBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableBottomBar bottomBar = findViewById(R.id.bottom_bar);

        bottomBar.setOnItemSelectedListener((view, menuItem, aBoolean) -> {
            switch (menuItem.getId()) {
                case R.id.setting:
                    Log.d("MainActivity", "setting press");
                    break;
                case R.id.home:
                    Log.d("MainActivity", "home press");
                    break;
                case R.id.other:
                    Log.d("MainActivity", "other press");
                    break;
            }

            return null;
        });
    }
}