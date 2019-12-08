package com.tt.reactive.source.gateway;

import com.tt.reactive.source.InsultSource;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class InsultGateway implements InsultSource {

    private static final int N_THREADS = 4;
    private static final HttpRequest ANOTHER_INSULT_REQUEST = HttpRequest.newBuilder()
            .uri(URI.create("https://evilinsult.com/generate_insult.php?lang=en"))
            .build();

    private final HttpClient client;

    public InsultGateway() {
        client = HttpClient.newBuilder()
                .executor(Executors.newFixedThreadPool(N_THREADS))
                .build();
    }

    @Override
    public CompletableFuture<String> nextInsultAsync() {
        return client.sendAsync(ANOTHER_INSULT_REQUEST, HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()))
                .thenApply(HttpResponse::body);
    }
}
