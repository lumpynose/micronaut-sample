package org.objecteffects.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest
public class HelloControllerTest {
    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void testHello() {
        final HttpRequest<?> request =
            HttpRequest.GET("/hello").accept(MediaType.TEXT_PLAIN);
        final String body = this.client.toBlocking().retrieve(request);

        assertNotNull(body);
        assertEquals("Hello World", body);
    }
}
