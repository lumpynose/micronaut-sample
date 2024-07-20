package com.objecteffects.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.runtime.Micronaut;

public class Application {
    final static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(final String[] args) {
        log.info("Application main");

        Micronaut.run(Application.class, args);
    }
}
