package com.tt.reactive.source.gateway;


import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

class InsultGatewayTest {

    @Test
    void getAnotherInsultSimpleExample() {
        InsultGateway gateway = new InsultGateway();

        CompletableFuture<String> anotherInsult = gateway.getAnotherInsult();

        String result = anotherInsult.join();
        assert result != null;
        assert !result.isBlank();
    }
}