package com.objecteffects.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import jakarta.annotation.PostConstruct;

/**
 */
@Controller("/hello")
public class HelloController {
    final static Logger log = LoggerFactory.getLogger(HelloController.class);

    @SuppressWarnings("nls")
    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        log.info("index get");

        return "Hello World";
    }

    @PostConstruct
    public void initialize() {
        log.info("initialize");
    }
}
