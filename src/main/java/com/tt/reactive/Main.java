package com.tt.reactive;

import reactor.core.publisher.Flux;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        InsultProcessor insultProcessor = new InsultProcessor();
        SentenceProcessor sentenceProcessor = new SentenceProcessor();

        Flux.merge(insultProcessor.getInsult(), sentenceProcessor.getSentence())
                .subscribe(System.out::println);

        Thread.sleep(60000);
    }
}
