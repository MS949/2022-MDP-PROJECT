package com.headthings.mdp_project_2022;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import butterknife.BindView;
import butterknife.ButterKnife;
import is.arontibo.library.ElasticDownloadView;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.elastic_download_view)
    ElasticDownloadView mElasticDownloadView;

    @BindView(R.id.loading_text)
    TextView loadingText;

    private final Handler mHandler = new Handler();

    private final String ServerIP = "tcp://192.168.0.100";
    private final String TOPIC = "TopicName";
    private MqttClient mqttClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this, this);

        SharedPreferences pref = getSharedPreferences(getString(R.string.isFirst), Activity.MODE_PRIVATE);
        boolean isFirst = pref.getBoolean(getString(R.string.isFirst), true);

        mHandler.post(() -> {
            try {
                mElasticDownloadView.startIntro();
                mElasticDownloadView.setProgress(50);
                loadingText.setText("ubuntuServer connect...");

                mqttClient = new MqttClient(ServerIP, MqttClient.generateClientId(), null);
//                mqttClient.connect();

                mElasticDownloadView.success();
                loadingText.setText("로딩 완료");

                try {
                    Thread.sleep(300);
                    startActivity(new Intent(this, MainActivity.class));
                    if (isFirst) {
                        startActivity(new Intent(this, FirstBootingActivity.class));
                    }
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (MqttException e) {
                mElasticDownloadView.fail();
                loadingText.setText("에러: " + e.getMessage());
                e.printStackTrace();
            }

        });
    }
}