package com.objecteffects.mqtt;

import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttException;

public class MqttMain {
    public static void main(String[] args) throws MqttException {
        String server = "192.168.50.5";
        String port = "1883";
        String[] topics = { "rtl_433/temperature/+", "zigbee/temperature/+" };
        int qos = 1;
        String broker;

        broker = "tcp://" + server + ":" + port;

        MqttClient client = new Connect().connect(server);
        
        try {
            new Listen().listen(client, topics, qos, new Listener(client));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
