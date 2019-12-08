package com.tt.reactive.topology;

import com.tt.reactive.source.gateway.InsultGateway;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class InsultSourceProcessor {

    private final InsultGateway gateway;
    private final Flux<String> insultFlux;

    public InsultSourceProcessor() {
        this(new InsultGateway());
    }

    public InsultSourceProcessor(InsultGateway insultGateway) {
        this.gateway = insultGateway;
        insultFlux = Flux.interval(Duration.ofMillis(2100))
                .flatMap(l -> Mono.fromFuture(gateway.getAnotherInsult()));
    }

    public Flux<String> getInsult() {
        return insultFlux;
    }
}
