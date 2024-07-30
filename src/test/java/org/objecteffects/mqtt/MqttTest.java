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
    @SuppressWarnings("static-method")
    void testItWorks() throws MqttException {
        final String broker = "tcp://192.168.50.3:1883";
        final String[] topics = { "rtl_433/temperature/+", "zigbee/temperature/+" };
        final int qos = 1;

        MqttClient client = new MqttConnect().connect(broker);
        
        try {
            new MqttListen().listen(client, topics, qos, new MqttListener());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
