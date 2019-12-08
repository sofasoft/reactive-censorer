package com.tt.reactive.source.gateway;


import com.github.tomakehurst.wiremock.WireMockServer;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

class InsultGatewayTest {


    static WireMockServer wireMockServer = new WireMockServer(4321);

    @BeforeAll
    static void startWireMock() {
        wireMockServer.start();
    }

    @AfterAll
    static void stopWireMock() {
        wireMockServer.stop();
    }

    @Test
    void nextInsultAsync() {
        String expectedMessage = "nextInsult";
        wireMockServer.stubFor(get(urlEqualTo("/nextInsult"))
                .willReturn(aResponse()
                        .withHeader("Content-type", "text/plain")
                        .withBody(expectedMessage)));

        InsultGateway gateway = new InsultGateway(
                URI.create("http://localhost:4321/nextInsult"),
                2,
                Duration.ofMillis(5000),
                Duration.ofMillis(10000));

        CompletableFuture<String> nextInsult = gateway.nextInsultAsync();

        String result = nextInsult.join();
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isBlank());
        Assertions.assertEquals(result, expectedMessage);
    }

    @Test
    void nextInsultAsyncConnErr() {
        InsultGateway gateway = new InsultGateway(URI.create("http://localhost:4322/nextInsult"),
                2,
                Duration.ofMillis(500),
                Duration.ofMillis(1000));

        CompletableFuture<String> nextInsult = gateway.nextInsultAsync();

        Awaitility.await("connection timeout")
                .until(nextInsult::isCompletedExceptionally);
    }

    @Test
    void nextInsultAsyncLongDelay() {
        String expectedMessage = "nextInsult";
        wireMockServer.stubFor(get(urlEqualTo("/nextInsult"))
                .willReturn(aResponse()
                        .withFixedDelay(5000)
                        .withHeader("Content-type", "text/plain")
                        .withBody(expectedMessage)));

        InsultGateway gateway = new InsultGateway(URI.create("http://localhost:4321/nextInsult"),
                2,
                Duration.ofMillis(500),
                Duration.ofMillis(1000));

        CompletableFuture<String> nextInsultAsync = gateway.nextInsultAsync();

        Awaitility.await("connection drop")
                .until(nextInsultAsync::isCompletedExceptionally);
    }
}