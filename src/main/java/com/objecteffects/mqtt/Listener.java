package com.objecteffects.mqtt;

import com.objecteffects.sensors.ProcessMessage;
import com.objecteffects.sensors.SensorData;

import org.eclipse.paho.mqttv5.client.IMqttMessageListener;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.annotation.Prototype;
import jakarta.inject.Inject;

@Prototype
public class Listener implements IMqttMessageListener {
    final static Logger log = LoggerFactory.getLogger(Listener.class);

    private MqttClient client;

    @Inject
    ProcessMessage processMessage;

    public Listener() {
        log.info("constructor");
    }

    @Override
    public void messageArrived(final String topic,
        final MqttMessage mqttMessage)
        throws Exception {
        final String messageTxt = new String(mqttMessage.getPayload());
        log.info("Message on {}: '{}'", topic, messageTxt);

        log.info("processMessage: {}", this.processMessage);
        
        final SensorData target =
            this.processMessage.processData(topic, messageTxt);
        log.info("target: {}", target);

        final MqttProperties props = mqttMessage.getProperties();
        final String responseTopic = props.getResponseTopic();

        if (responseTopic != null) {
            log.info("--Response topic: {}", responseTopic);
            final String corrData = new String(props.getCorrelationData());

            final MqttMessage response = new MqttMessage();
            final MqttProperties responseProps = new MqttProperties();
            responseProps.setCorrelationData(corrData.getBytes());
            final String content =
                "Got message with correlation data " + corrData;
            response.setPayload(content.getBytes());
            response.setProperties(props);

            this.client.publish(responseTopic, response);
        }
    }

    public void setClient(final MqttClient _client) {
        this.client = _client;
    }
}
