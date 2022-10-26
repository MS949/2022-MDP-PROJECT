package com.headthings.mdp_project_2022;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;
import is.arontibo.library.ElasticDownloadView;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.elastic_download_view)
    ElasticDownloadView mElasticDownloadView;

    @BindView(R.id.loading_text)
    TextView loadingText;

//    WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
//    WifiConfiguration wifiConfiguration = new WifiConfiguration();

    MqttClient mqtt = Mqtt.getInstance();

    private final String networkSSID = "ssid";
    private final String networkPass = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this, this);

//        wifiConfiguration.SSID = "\"" + networkSSID + "\"";

        SharedPreferences pref = getSharedPreferences(getString(R.string.isFirst), Activity.MODE_PRIVATE);
        boolean isFirst = pref.getBoolean(getString(R.string.isFirst), true);

        AtomicBoolean error = new AtomicBoolean(true);

        mElasticDownloadView.startIntro();

        new Handler().post(() -> {
            mElasticDownloadView.setProgress(0);
            loadingText.setText("WIFI CHECK");

            mElasticDownloadView.setProgress(50);
            loadingText.setText("Server connect...");
            try {
                mqtt.connect();
                if (!mqtt.isConnected()) {
                    throw new MqttException(MqttException.REASON_CODE_SERVER_CONNECT_ERROR);
                }
                mqtt.publish("device/connect", "android connected".getBytes(), 0, false);

            } catch (MqttException e) {
                mElasticDownloadView.fail();
                loadingText.setText("에러: " + e.getMessage());
                error.set(false);
            }

            if (error.get()) {
                mElasticDownloadView.success();
                loadingText.setText("로딩 완료");
            }

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            if (isFirst) {
                startActivity(new Intent(getApplicationContext(), FirstBootingActivity.class));
            }
            finish();

        });


    }

//    void one() {
//        if (wifiManager.isWifiEnabled()) {
//            wifiManager.setWifiEnabled(false);
//        } else {
//            if (wifiManager.getWifiState() != WifiManager.WIFI_STATE_ENABLING) {
//                wifiManager.setWifiEnabled(true);
//            }
//        }
//    }
}