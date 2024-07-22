package com.objecteffects.mqtt;

import java.util.UUID;

import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Singleton;

@Singleton
public class Connect {
    final static Logger log = LoggerFactory.getLogger(Connect.class);

    private static MemoryPersistence persistence = new MemoryPersistence();

    public MqttClient connect(String broker) throws MqttException {
        MqttClient client;
        String clientId = UUID.randomUUID().toString();

        try {
            log.info("Connecting to MQTT broker: {}", broker);

            MqttConnectionOptions connOpts = new MqttConnectionOptions();
            connOpts.setCleanStart(false);

            client = new MqttClient(broker, clientId, persistence);
            client.connect(connOpts);

            log.info("Connected");
        }
        catch (MqttException me) {
            log.error("reason {}", me.getReasonCode());
            log.error("msg {}", me.getMessage());
            log.error("loc {}", me.getLocalizedMessage());
            log.error("cause {}", me.getCause());
            
            log.error("exception: {}", me);

            throw me;
        }

        return client;
    }

    public void disconnect(MqttClient client) {
        try {
            client.disconnect();
            log.info("Disconnected");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
