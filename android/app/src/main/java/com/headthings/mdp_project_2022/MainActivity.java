package com.headthings.mdp_project_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import github.com.st235.lib_expandablebottombar.ExpandableBottomBar;
import github.com.st235.lib_expandablebottombar.Menu;

public class MainActivity extends AppCompatActivity {

    private ExpandableBottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBar = findViewById(R.id.bottom_bar);
    }

}