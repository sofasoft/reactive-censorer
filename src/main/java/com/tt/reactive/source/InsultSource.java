package com.tt.reactive.source;

import java.util.concurrent.CompletableFuture;

public interface InsultSource extends DataSource {

    CompletableFuture<String> nextInsultAsync();
}
