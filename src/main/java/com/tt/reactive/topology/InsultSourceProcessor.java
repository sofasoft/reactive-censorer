package com.tt.reactive.topology;

import com.tt.reactive.source.gateway.InsultGateway;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class InsultSourceProcessor {

    private static final Duration DEFAULT_INTERVAL = Duration.ofMillis(2100);
    private final InsultGateway gateway;
    private final Flux<String> insultFlux;

    public InsultSourceProcessor() {
        this(new InsultGateway(), DEFAULT_INTERVAL);
    }

    public InsultSourceProcessor(InsultGateway insultGateway, Duration interval) {
        this.gateway = insultGateway;
        insultFlux = Flux.interval(interval)
                .flatMap(l -> Mono.fromFuture(gateway.nextInsultAsync()));
    }

    public Flux<String> getInsultSource() {
        return insultFlux;
    }
}
