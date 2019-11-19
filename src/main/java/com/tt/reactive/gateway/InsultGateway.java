package com.tt.reactive.gateway;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

public class InsultGateway {

    private final HttpClient client;

    private static final HttpRequest ANOTHER_INSULT_REQUEST = HttpRequest.newBuilder()
            .uri(URI.create("https://evilinsult.com/generate_insult.php?lang=en"))
            .build();

    public InsultGateway() {
        client = HttpClient.newHttpClient();
    }

    public CompletableFuture<String> getAnotherInsult() {
        return client.sendAsync(ANOTHER_INSULT_REQUEST, HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()))
                .thenApply(HttpResponse::body);
    }
}
