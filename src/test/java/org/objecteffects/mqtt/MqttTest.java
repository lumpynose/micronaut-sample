package org.objecteffects.mqtt;

import com.objecteffects.mqtt.MqttConnect;
import com.objecteffects.mqtt.MqttListen;
import com.objecteffects.mqtt.MqttListener;

import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.junit.jupiter.api.Test;

/*
 * Wrong.
 */
class MqttTest {
    @Test
    void testItWorks() throws MqttException {
        String serverIp = "192.168.50.5";
        String port = "1883";
        String[] topics = { "rtl_433/temperature/+", "zigbee/temperature/+" };
        int qos = 1;
        String broker;

        broker = "tcp://" + serverIp + ":" + port;

        MqttClient client = new MqttConnect().connect(broker);
        
        try {
            new MqttListen().listen(client, topics, qos, new MqttListener());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
