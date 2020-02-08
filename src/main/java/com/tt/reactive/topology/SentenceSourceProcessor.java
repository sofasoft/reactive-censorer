package com.tt.reactive.topology;

import com.tt.reactive.source.SentenceSource;
import com.tt.reactive.source.io.BookSentenceExtractor;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class SentenceSourceProcessor {

    private static final Duration DEFAULT_INTERVAL = Duration.ofMillis(500);
    private Flux<String> sentenceFlux;

    public SentenceSourceProcessor() {
        this(new BookSentenceExtractor(), DEFAULT_INTERVAL);
    }

    public SentenceSourceProcessor(SentenceSource sentenceSource, Duration interval) {
        sentenceFlux = Flux.interval(interval)
                .map(i -> sentenceSource.next());
    }

    public Flux<String> getSentenceSource() {
        return sentenceFlux;
    }
}
