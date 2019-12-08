package com.tt.reactive.topology;

import com.tt.reactive.source.SentenceSource;
import com.tt.reactive.source.io.BookSentenceExtractor;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class SentenceSourceProcessor {

    private Flux<String> sentenceFlux;

    public SentenceSourceProcessor() {
        this(new BookSentenceExtractor());
    }

    public SentenceSourceProcessor(SentenceSource sentenceSource) {
        sentenceFlux = Flux.interval(Duration.ofMillis(500))
                .map(i -> sentenceSource.nextSentence());
    }

    public Flux<String> getSentenceSource() {
        return sentenceFlux;
    }
}
