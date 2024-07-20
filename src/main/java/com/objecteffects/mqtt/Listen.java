package com.objecteffects.mqtt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.paho.mqttv5.client.IMqttMessageListener;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttSubscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listen {
    final static Logger log = LoggerFactory.getLogger(Listen.class);
    
    public void listen(MqttClient client, String[] topics, int qos,
        IMqttMessageListener listener) {

    List<MqttSubscription> subs = new ArrayList<>();

    for (String topic : topics) {
        log.info("Subscribing to topic " + topic);

        subs.add(new MqttSubscription(topic, qos));
    }

    IMqttMessageListener[] listeners = new Listener[subs.size()];
    Arrays.fill(listeners, listener);

    try {
        client.subscribe(subs.toArray(new MqttSubscription[0]), listeners);

//        for (String topic : topics) {
//            log.info("Subscribing to topic " + topic);
//            client.subscribe(topic, qos, listener);
//        }
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}

}
