package com.objecteffects.web;

import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import jakarta.inject.Inject;

import com.objecteffects.mqtt.Connect;
import com.objecteffects.mqtt.Listen;
import com.objecteffects.mqtt.Listener;

@Singleton
public class MqttStartupCli
    implements ApplicationEventListener<ServerStartupEvent> {
    final static Logger log = LoggerFactory.getLogger(MqttStartupCli.class);

    final String broker = "tcp://192.168.50.5:1883";
    final String[] topics = { "rtl_433/temperature/+", "zigbee/temperature/+" };
    final int qos = 1;

    @Inject
    Connect connect;

    @Inject
    Listen listen;

    @Inject
    Listener listener;

    @Override
    public void onApplicationEvent(final ServerStartupEvent startupEvent) {
        log.info("MqttStartup ServerStartupEvent");

        try {
            MqttClient client = this.connect.connect(this.broker);

            this.listener.setClient(client);

            this.listen.listen(client, this.topics, this.qos, this.listener);
        }
        catch (MqttException e) {
            log.error("exception: {}", e);
        }
    }

    @PostConstruct
    public void initialize() {
        log.info("initialize");
    }
}
