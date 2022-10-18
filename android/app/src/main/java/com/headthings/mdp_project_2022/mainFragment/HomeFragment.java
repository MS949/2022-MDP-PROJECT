package com.headthings.mdp_project_2022.mainFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.headthings.mdp_project_2022.R;
import com.headthings.mdp_project_2022.SplashActivity;

import org.eclipse.paho.client.mqttv3.MqttException;

import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class HomeFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnCheckedChanged({R.id.switch1, R.id.switch2, R.id.switch3})
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        try {
            switch (compoundButton.getId()) {
                case R.id.switch1:
                    if (isChecked) {
                        SplashActivity.mqttClient.publish("test/test1", "on".getBytes(), 0, false);
                    } else {
                        SplashActivity.mqttClient.publish("test/test1", "off".getBytes(), 0, false);
                    }
                    break;
                case R.id.switch2:
                    if (isChecked) {
                        SplashActivity.mqttClient.publish("test/test2", "on".getBytes(), 0, false);
                    } else {
                        SplashActivity.mqttClient.publish("test/test2", "off".getBytes(), 0, false);
                    }
                    break;
                case R.id.switch3:
                    if (isChecked) {
                        SplashActivity.mqttClient.publish("test/test3", "on".getBytes(), 0, false);
                    } else {
                        SplashActivity.mqttClient.publish("test/test3", "off".getBytes(), 0, false);
                    }
                    break;
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
