package com.objecteffects.web;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.objecteffects.sensors.Sensors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jooq.DSLContext;
import org.jooq.Param;
import org.jooq.SQLDialect;
import org.jooq.Query;
import org.jooq.impl.DSL;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import jakarta.inject.Inject;

@Controller
class ViewsController {
    final static Logger log = LoggerFactory.getLogger(ViewsController.class);

    @Inject
    Sensors sensors;

    @Inject
    DataSource ds;

    @View("home")
    @Get("/map")
    public HttpResponse<?> index() {
        log.info("index get map");

        return HttpResponse
            .ok(CollectionUtils.mapOf("loggedIn", true, "username",
                "sdelamo-map"));
    }

    @View("home")
    @Get("/pojo")
    public HttpResponse<Person> pojo() {
        log.info("index get pojo");

        return HttpResponse.ok(new Person("sdelamo-pojo", true));
    }

    @View("home")
    @Get("/pojo2")
    public Person pojo2() {
        log.info("index get pojo2");

        return new Person("sdelamo-pojo2", true);
    }

    @View("mqtt")
    @Get("/sensors")
    public Sensors sensors() {
        log.info("index get sensors");

        return this.sensors;
    }
}
