package com.tt.reactive;

import com.tt.reactive.InsultProcessor;
import com.tt.reactive.gateway.InsultGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import java.util.concurrent.CompletableFuture;

class InsultProcessorTest {

    @Test
    void getInsultSimpleExample() throws InterruptedException {
        InsultGateway mock = Mockito.mock(InsultGateway.class);
        Mockito.when(mock.getAnotherInsult())
                .thenReturn(CompletableFuture.supplyAsync(() -> "Some sentence1"))
                .thenReturn(CompletableFuture.supplyAsync(() -> "Some sentence2"));

        InsultProcessor insultProcessor = new InsultProcessor(mock);

        StepVerifier.create(insultProcessor.getInsult().take(2))
                .expectNext("Some sentence1")
                .expectNext("Some sentence2")
                .verifyComplete();
    }
}
