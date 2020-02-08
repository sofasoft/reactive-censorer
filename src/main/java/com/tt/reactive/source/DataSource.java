package com.tt.reactive.source;

import java.util.concurrent.CompletableFuture;

public interface DataSource<T> {
    CompletableFuture<T> nextAsync();
    T next();
}
