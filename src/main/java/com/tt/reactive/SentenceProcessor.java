package com.tt.reactive;

import com.tt.reactive.io.BookSentenceExtractor;
import com.tt.reactive.io.SentenceSource;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class SentenceProcessor {

    private Flux<String> sentenceFlux;

    public SentenceProcessor() {
        this(new BookSentenceExtractor());
    }

    public SentenceProcessor(SentenceSource sentenceSource) {
        sentenceFlux = Flux.interval(Duration.ofMillis(500))
                .map(i -> sentenceSource.nextSentence());
    }

    public Flux<String> getSentence() {
        return sentenceFlux;
    }
}
