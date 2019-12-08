package com.tt.reactive.source.gateway;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

//todo wiremock
//todo badly path tests
class InsultGatewayTest {

    @Test
    void getAnotherInsultSimpleExample() {
        InsultGateway gateway = new InsultGateway();

        CompletableFuture<String> anotherInsult = gateway.nextInsultAsync();

        String result = anotherInsult.join();
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isBlank());
    }
}