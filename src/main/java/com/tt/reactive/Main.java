package com.tt.reactive;

import com.tt.reactive.topology.InsultSourceProcessor;
import com.tt.reactive.topology.SentenceSourceProcessor;
import reactor.core.publisher.Flux;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        InsultSourceProcessor insultSourceProcessor = new InsultSourceProcessor();
        SentenceSourceProcessor sentenceSourceProcessor = new SentenceSourceProcessor();

        Flux.merge(insultSourceProcessor.getInsult(), sentenceSourceProcessor.getSentenceSource())
                .subscribe(System.out::println);

        Thread.sleep(60000);
    }
}
