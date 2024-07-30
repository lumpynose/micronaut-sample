package org.objecteffects.mqtt;

import com.objecteffects.mqtt.MqttConnect;
import com.objecteffects.mqtt.MqttListen;
import com.objecteffects.mqtt.MqttListener;
import com.objecteffects.sensors.Sensors;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

import static java.time.Duration.*;
import static java.util.concurrent.TimeUnit.*;

import org.awaitility.Awaitility;

@MicronautTest
class MqttTest {
    final static Logger log = LoggerFactory.getLogger(MqttTest.class);

    @Inject
    EmbeddedServer server;

    @Inject
    ApplicationContext context;

    @Inject
    MqttConnect connect;

    @Inject
    MqttListen listen;

    @Inject
    MqttListener listener;

    @Inject
    Sensors sensors;

//    @BeforeAll
//    static void startContainer(EmbeddedServer server) {
//        log.info("environment: {}", server.getEnvironment());
//
//        try (EmbeddedServer xserver = server.start()) {
//        }
//    }

    @Test
    void testItWorks() throws MqttException {
        final String broker = "tcp://192.168.50.3:1883";
        final String[] topics =
            { "rtl_433/temperature/+", "zigbee/temperature/+" };
        final int qos = 1;

        MqttClient client = this.connect.connect(broker);
        this.listener.setClient(client);
        this.listen.listen(client, topics, qos, new MqttListener());

        Awaitility.setDefaultPollInterval(1000, MILLISECONDS);

        Awaitility.await().until(() -> {
            log.info("trying ...");
            
            return !this.sensors.getSensors().isEmpty();
        });
    }
}
