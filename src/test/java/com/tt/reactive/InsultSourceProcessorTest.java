package com.tt.reactive;

import com.tt.reactive.source.gateway.InsultGateway;
import com.tt.reactive.topology.InsultSourceProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import java.util.concurrent.CompletableFuture;

class InsultSourceProcessorTest {

    @Test
    void getInsultSimpleExample() throws InterruptedException {
        InsultGateway mock = Mockito.mock(InsultGateway.class);
        Mockito.when(mock.getAnotherInsult())
                .thenReturn(CompletableFuture.supplyAsync(() -> "Some sentence1"))
                .thenReturn(CompletableFuture.supplyAsync(() -> "Some sentence2"));

        InsultSourceProcessor insultSourceProcessor = new InsultSourceProcessor(mock);

        StepVerifier.create(insultSourceProcessor.getInsult().take(2))
                .expectNext("Some sentence1")
                .expectNext("Some sentence2")
                .verifyComplete();
    }
}
