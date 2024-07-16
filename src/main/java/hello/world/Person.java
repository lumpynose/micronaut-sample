package hello.world;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Person(String username, Boolean loggedIn) {
}
