package com.headthings.mdp_project_2022.mainFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.headthings.mdp_project_2022.Mqtt;
import com.headthings.mdp_project_2022.OneClickView;
import com.headthings.mdp_project_2022.R;

import org.eclipse.paho.client.mqttv3.MqttClient;
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

    MqttClient mqtt = Mqtt.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        led.setOnCheckedChangeListener((compoundButton, b) -> {
            try {
                if (b) {
                    Log.d("iot/led", "on");
                    mqtt.publish("iot/led", "on".getBytes(), 0, false);
                } else {
                    Log.d("iot/led", "off");
                    mqtt.publish("iot/led", "off".getBytes(), 0, false);
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });

        curtain.setOnCheckedChangeListener((compoundButton, b) -> {
            try {
                if (b) {
                    Log.d("iot/curtain", "on");
                    mqtt.publish("iot/curtain", "on".getBytes(), 0, false);
                } else {
                    Log.d("iot/curtain", "off");
                    mqtt.publish("iot/curtain", "off".getBytes(), 0, false);
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });

        heatingPad.setOnCheckedChangeListener((compoundButton, b) -> {
            try {
                if (b) {
                    Log.d("iot/heating_pad", "on");
                    mqtt.publish("iot/heating_pad", "on".getBytes(), 0, false);
                } else {
                    Log.d("iot/heating_pad", "off");
                    mqtt.publish("iot/heating_pad", "off".getBytes(), 0, false);
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });

        door.setOnCheckedChangeListener((compoundButton, b) -> {
            try {
                if (b) {
                    Log.d("iot/door", "on");
                    mqtt.publish("iot/door", "on".getBytes(), 0, false);
                } else {
                    Log.d("iot/door", "off");
                    mqtt.publish("iot/door", "off".getBytes(), 0, false);
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });

        new Handler().postDelayed(() -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle("오류").setMessage("WiFi가 비활성화 되었습니다.");

            builder.setPositiveButton("재시도", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(), "재시도", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }, 10000);

        return view;
    }

}
