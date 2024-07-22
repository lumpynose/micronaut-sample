package com.objecteffects.sensors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.annotation.Prototype;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

@Prototype
public class ProcessMessage {
    private final static Logger log = LoggerFactory
        .getLogger(ProcessMessage.class);

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    private static TUnit tunit = TUnit.Fahrenheit; // MainPaho.getTunit();
    
    @Inject
    Sensors sensors;
    
    // private static Map<String, String> propSensors =
    // MainPaho.getPropSensors();

    public ProcessMessage() {
        log.info("constructor");
    }

    public SensorData processData(final String topic, final String data) {
        final String topic_trimmed = StringUtils.substringAfterLast(topic, "/");

        log.info("topic: {}", topic_trimmed);

        final Gson gson = new Gson();

        final SensorData target = gson.fromJson(data, SensorData.class);

//        if (!propSensors.containsKey(topic_trimmed)) {
//            return null;
//        }

        // target.setSensorName(propSensors.get(topic_trimmed));

        target.setSensorName(topic_trimmed);
        target.setTemperatureShow((float) tunit.convert(target));
        target.setTemperatureLetter(tunit.toString());

        final LocalDateTime dateTime = LocalDateTime.now();

        target.setTimestamp(dtf.format(dateTime));

        log.info("decoded data: {}", target.toString());

        sensors.addSensor(target);
        
        return target;
    }

    @PostConstruct
    public void initialize() {
        log.info("initialize");
    }
}
