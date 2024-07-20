package org.objecteffects.mqtt;

import com.objecteffects.mqtt.Connect;
import com.objecteffects.mqtt.Listen;
import com.objecteffects.mqtt.Listener;

import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.junit.jupiter.api.Test;

class MqttTest {
    @Test
    void testItWorks() throws MqttException {
        String serverIp = "192.168.50.5";
        String port = "1883";
        String[] topics = { "rtl_433/temperature/+", "zigbee/temperature/+" };
        int qos = 1;
        String broker;

        broker = "tcp://" + serverIp + ":" + port;

        MqttClient client = new Connect().connect(broker);
        
        try {
            new Listen().listen(client, topics, qos, new Listener(client));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
