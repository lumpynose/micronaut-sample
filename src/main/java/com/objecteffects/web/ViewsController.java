package com.objecteffects.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

@Controller("/views")
class ViewsController {
    final static Logger log = LoggerFactory.getLogger(ViewsController.class);

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
}
