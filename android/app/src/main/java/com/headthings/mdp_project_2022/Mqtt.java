package com.headthings.mdp_project_2022;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Mqtt {
    private static final String ServerIP = "tcp://192.168.0.100:1883";
    public static MqttClient mqttClient;

    static {
        try {
            mqttClient = new MqttClient(ServerIP, MqttClient.generateClientId(), null);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
