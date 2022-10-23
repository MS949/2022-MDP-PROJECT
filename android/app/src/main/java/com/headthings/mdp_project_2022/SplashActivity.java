package com.headthings.mdp_project_2022;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this, this);

//        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
//        Network currentNetwork = connectivityManager.getActiveNetwork();
//        LinkProperties linkProperties = connectivityManager.getLinkProperties(currentNetwork);
//        NetworkCapabilities caps = connectivityManager.getNetworkCapabilities(currentNetwork);

//        connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
//
//            // 새 네트워크가 기본값
//            @Override
//            public void onAvailable(@NonNull Network network) {
//                Log.e("onAvailable", "기본 네트워크: " + network);
//            }
//
//            // 네트워크가 기본 네트워크로 설정된 상태를 상실
//            @Override
//            public void onLost(@NonNull Network network) {
//                Log.e("onLost", "기본 네트워크 연결이 해제되었습니다. 마지막 기본 네트워크: " + network);
//            }
//
//            //
//            @Override
//            public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
//                Log.e("onCapabilitiesChanged", "기본 네트워크 변경 값: " + networkCapabilities);
//            }
//
//            @Override
//            public void onLinkPropertiesChanged(@NonNull Network network, @NonNull LinkProperties linkProperties) {
//                Log.e("onLinkPropertiesChanged", "기본 네트워크 변경 링크 값: " + linkProperties);
//            }
//        });

        SharedPreferences pref = getSharedPreferences(getString(R.string.isFirst), Activity.MODE_PRIVATE);
        boolean isFirst = pref.getBoolean(getString(R.string.isFirst), true);

        AtomicBoolean error = new AtomicBoolean(true);

        mHandler.post(() -> {
            mElasticDownloadView.startIntro();

            mElasticDownloadView.setProgress(0);
            loadingText.setText("WIFI CHECK");
        });

        mHandler.postDelayed(() -> {
            try {
                mElasticDownloadView.setProgress(50);
                loadingText.setText("Server connect...");

                Mqtt.mqttClient.connect();
                Mqtt.mqttClient.publish("device/connect", "android connected".getBytes(), 0, false);

            } catch (MqttException e) {
                mElasticDownloadView.fail();
                loadingText.setText("에러: " + e.getMessage());
                error.set(false);
            }
        }, 500);

        mHandler.postDelayed(() -> {
            if (error.get()) {
                mElasticDownloadView.success();
                loadingText.setText("로딩 완료");
            }
        }, 1500);

        mHandler.postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            if (isFirst) {
                startActivity(new Intent(this, FirstBootingActivity.class));
            }
            finish();
        }, 3000);
    }
}