package com.objecteffects.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

@Singleton
public class MqttStartupCli
    implements ApplicationEventListener<ServerStartupEvent> {
    final static Logger log = LoggerFactory.getLogger(MqttStartupCli.class);

    @Override
    public void onApplicationEvent(final ServerStartupEvent startupEvent) {
        log.info("MqttStartup ServerStartupEvent");
    }

    @PostConstruct
    public void initialize() {
        log.info("initialize");
    }
}
