package com.tt.reactive.gateway;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class InsultProcessor {

    private final InsultGateway gateway = new InsultGateway();
    private final Flux<String> insultFlux;

    public InsultProcessor() {
        insultFlux = Flux.interval(Duration.ofMillis(2100))
                .flatMap(l -> Mono.fromFuture(gateway.getAnotherInsult()));
    }

    public Flux<String> getInsult() {
        return insultFlux;
    }
}
