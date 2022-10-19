package com.headthings.mdp_project_2022.mainFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.headthings.mdp_project_2022.OneClickView;
import com.headthings.mdp_project_2022.R;
import com.headthings.mdp_project_2022.SplashActivity;

import org.eclipse.paho.client.mqttv3.MqttException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.curtain)
    OneClickView curtain;

    @BindView(R.id.camera)
    OneClickView camera;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        curtain.setOnCheckedChangeListener((compoundButton, b) -> {
            try {
                if (b) {
                    SplashActivity.mqttClient.publish("test/test1", "on".getBytes(), 0, false);
                } else {
                    SplashActivity.mqttClient.publish("test/test1", "off".getBytes(), 0, false);
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });
        camera.setOnCheckedChangeListener((compoundButton, b) -> {
            try {
                if (b) {
                    SplashActivity.mqttClient.publish("test/test2", "on".getBytes(), 0, false);
                } else {
                    SplashActivity.mqttClient.publish("test/test2", "off".getBytes(), 0, false);
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });

        return view;
    }

}
