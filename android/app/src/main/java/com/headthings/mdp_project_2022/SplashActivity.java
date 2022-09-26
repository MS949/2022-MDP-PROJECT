package com.headthings.mdp_project_2022;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import is.arontibo.library.ElasticDownloadView;

public class SplashActivity extends AppCompatActivity {

    ElasticDownloadView mElasticDownloadView;
    TextView loadingText;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mElasticDownloadView = findViewById(R.id.elastic_download_view);

        loadingText = findViewById(R.id.loading_text);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.isFirst), Activity.MODE_PRIVATE);
        boolean isFirst = sharedPreferences.getBoolean(getString(R.string.isFirst), true);

        mHandler.post(() -> mElasticDownloadView.startIntro());
        for (int i = 0; i < 10; i++) {
            int tmp = i;
            mHandler.postDelayed(() -> {
                mElasticDownloadView.setProgress(tmp * 10);
                loadingText.setText(tmp * 10 + "%");
            }, tmp * 1000);
        }

        mHandler.postDelayed(() -> {
            mElasticDownloadView.success();
            loadingText.setText("로딩 완료");
        }, 10 * 1000);

        mHandler.postDelayed(() -> {
            if (isFirst) {
//            SharedPreferences.Editor editor = pref.edit();
//            editor.putBoolean(getString(R.string.isFirst),false);
//            editor.apply();

                startActivity(new Intent(this, FirstBootingActivity.class));
                finish();
            }
        }, 12500);

    }

}