package com.tt.reactive;

import com.tt.reactive.gateway.InsultProcessor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        InsultProcessor insultProcessor = new InsultProcessor();
        insultProcessor.getInsult().subscribe(System.out::println);

        Thread.sleep(60000);
    }
}
