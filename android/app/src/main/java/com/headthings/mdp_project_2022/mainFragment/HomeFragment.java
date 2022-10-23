package com.headthings.mdp_project_2022.mainFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.headthings.mdp_project_2022.Mqtt;
import com.headthings.mdp_project_2022.OneClickView;
import com.headthings.mdp_project_2022.R;

import org.eclipse.paho.client.mqttv3.MqttException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.bulb)
    OneClickView led;

    @BindView(R.id.curtain)
    OneClickView curtain;

    @BindView(R.id.heating_pad)
    OneClickView heatingPad;

    @BindView(R.id.door)
    OneClickView door;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        led.setOnCheckedChangeListener((compoundButton, b) -> {
            try {
                if (b) {
                    Log.d("iot/led", "on");
                    Mqtt.mqttClient.publish("iot/led", "on".getBytes(), 0, false);
                } else {
                    Log.d("iot/led", "off");
                    Mqtt.mqttClient.publish("iot/led", "off".getBytes(), 0, false);
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });

        curtain.setOnCheckedChangeListener((compoundButton, b) -> {
            try {
                if (b) {
                    Log.d("iot/curtain", "on");
                    Mqtt.mqttClient.publish("iot/curtain", "on".getBytes(), 0, false);
                } else {
                    Log.d("iot/curtain", "off");
                    Mqtt.mqttClient.publish("iot/curtain", "off".getBytes(), 0, false);
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });

        heatingPad.setOnCheckedChangeListener((compoundButton, b) -> {
            try {
                if (b) {
                    Log.d("iot/heating_pad", "on");
                    Mqtt.mqttClient.publish("iot/heating_pad", "on".getBytes(), 0, false);
                } else {
                    Log.d("iot/heating_pad", "off");
                    Mqtt.mqttClient.publish("iot/heating_pad", "off".getBytes(), 0, false);
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });

        door.setOnCheckedChangeListener((compoundButton, b) -> {
            try {
                if (b) {
                    Log.d("iot/door", "on");
                    Mqtt.mqttClient.publish("iot/door", "on".getBytes(), 0, false);
                } else {
                    Log.d("iot/door", "off");
                    Mqtt.mqttClient.publish("iot/door", "off".getBytes(), 0, false);
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });

        return view;
    }

}
