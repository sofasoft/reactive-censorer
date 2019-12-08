package com.tt.reactive.source.gateway;

import com.tt.reactive.source.InsultSource;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class InsultGateway implements InsultSource {

    private final HttpRequest anotherInsultRequest;
    private final HttpClient client;

    public InsultGateway() {
        this(
                URI.create("https://evilinsult.com/generate_insult.php?lang=en"),
                4,
                Duration.ofMillis(2000),
                Duration.ofMillis(10000));
    }

    public InsultGateway(URI uri, int nThreads, Duration timeout, Duration connectionTimeout) {
        anotherInsultRequest = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(timeout)
                .build();

        client = HttpClient.newBuilder()
                .executor(Executors.newFixedThreadPool(nThreads))
                .connectTimeout(connectionTimeout)
                .build();
    }

    @Override
    public CompletableFuture<String> nextInsultAsync() {
        return client.sendAsync(anotherInsultRequest, HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()))
                .thenApply(HttpResponse::body);
    }
}
