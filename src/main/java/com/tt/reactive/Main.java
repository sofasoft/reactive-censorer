package com.tt.reactive;

import com.tt.reactive.source.DataSource;
import com.tt.reactive.source.gateway.InsultGateway;
import com.tt.reactive.source.io.BookSentenceExtractor;
import com.tt.reactive.topology.Pipeline;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        DataSource[] sources = { new InsultGateway(), new BookSentenceExtractor() };


        Pipeline pipeline = new Pipeline(sources);
        pipeline.getMessagesProcessor()
                .subscribe(System.out::println);

        Thread.sleep(60000);
    }
}
