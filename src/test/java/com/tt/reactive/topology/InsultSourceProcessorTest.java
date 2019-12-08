package com.tt.reactive.topology;

import com.tt.reactive.source.gateway.InsultGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

class InsultSourceProcessorTest {

    @Test
    void getInsultSimpleExample() throws InterruptedException {
        InsultGateway mock = Mockito.mock(InsultGateway.class);

        String expectedInsult1 = "Some sentence1";
        String expectedInsult2 = "Some sentence2";

        Mockito.when(mock.nextInsultAsync())
                .thenReturn(CompletableFuture.supplyAsync(() -> expectedInsult1))
                .thenReturn(CompletableFuture.supplyAsync(() -> expectedInsult2));

        InsultSourceProcessor insultSourceProcessor = new InsultSourceProcessor(mock, Duration.ZERO);

        StepVerifier.create(insultSourceProcessor.getInsultSource().take(2))
                .expectNext(expectedInsult1)
                .expectNext(expectedInsult2)
                .verifyComplete();
    }
}
