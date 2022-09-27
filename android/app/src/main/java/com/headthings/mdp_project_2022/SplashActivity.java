package com.headthings.mdp_project_2022;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import is.arontibo.library.ElasticDownloadView;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.elastic_download_view)
    ElasticDownloadView mElasticDownloadView;

    @BindView(R.id.loading_text)
    TextView loadingText;

    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this, this);

        SharedPreferences pref = getSharedPreferences(getString(R.string.isFirst), Activity.MODE_PRIVATE);
        boolean isFirst = pref.getBoolean(getString(R.string.isFirst), true);

        mHandler.post(() -> mElasticDownloadView.startIntro());
        for (int i = 0; i < 10; i++) {
            int tmp = i;
            mHandler.postDelayed(() -> {
                mElasticDownloadView.setProgress(tmp * 10);
                loadingText.setText(tmp * 10 + "%");
            }, tmp * 500);
        }

        mHandler.postDelayed(() -> {
            mElasticDownloadView.success();
            loadingText.setText("로딩 완료");
        }, 10 * 500);

        mHandler.postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            if (isFirst) {
                startActivity(new Intent(this, FirstBootingActivity.class));
            }
            finish();
        }, 12 * 500);

    }

}