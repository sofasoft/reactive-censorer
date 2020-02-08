package com.tt.reactive.source.gateway;

import com.tt.reactive.source.InsultSource;

import java.io.IOException;
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
    public static final HttpResponse.BodyHandler<String> RESPONSE_BODY_HANDLER = HttpResponse.BodyHandlers.ofString(Charset.defaultCharset());

    private final HttpClient client;

    public InsultGateway() {
        client = HttpClient.newBuilder()
                .executor(Executors.newFixedThreadPool(N_THREADS))
                .build();
    }

    @Override
    public CompletableFuture<String> nextAsync() {
        return client.sendAsync(ANOTHER_INSULT_REQUEST, RESPONSE_BODY_HANDLER)
                .thenApply(HttpResponse::body);
    }

    @Override
    public String next() {
        try {
            return client.send(ANOTHER_INSULT_REQUEST, RESPONSE_BODY_HANDLER)
                    .body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
