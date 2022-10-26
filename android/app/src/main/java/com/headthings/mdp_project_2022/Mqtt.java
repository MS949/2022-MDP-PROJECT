package com.headthings.mdp_project_2022;

import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Mqtt implements MqttCallback {
    private final String ServerIP = "tcp://192.168.0.100:1883";
    private static MqttClient mqttClient;

    private Mqtt() {
        try {
            mqttClient = new MqttClient(ServerIP, MqttClient.generateClientId(), null);
            mqttClient.setCallback(this);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static MqttClient getInstance() {
        if (mqttClient == null) {
            new Mqtt();
        }
        return mqttClient;
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

}
