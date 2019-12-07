package com.tt.reactive;

import com.tt.reactive.gateway.InsultGateway;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class InsultProcessor {

    private final InsultGateway gateway;
    private final Flux<String> insultFlux;

    public InsultProcessor() {
        this(new InsultGateway());
    }

    public InsultProcessor(InsultGateway insultGateway) {
        this.gateway = insultGateway;
        insultFlux = Flux.interval(Duration.ofMillis(2100))
                .flatMap(l -> Mono.fromFuture(gateway.getAnotherInsult()));
    }

    public Flux<String> getInsult() {
        return insultFlux;
    }
}
