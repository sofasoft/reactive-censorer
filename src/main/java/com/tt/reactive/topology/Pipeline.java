package com.tt.reactive.topology;

import com.tt.reactive.source.DataSource;
import com.tt.reactive.source.domain.Comment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pipeline {

    private final Flux<Comment> messagesProcessor;

    public Pipeline(DataSource<String>... dataSources) {
        List<Flux<String>> intervals = Arrays.stream(dataSources)
                .map(this::wrapIntoIntervalOf3Seconds)
                .collect(Collectors.toList());

        messagesProcessor = Flux.merge(intervals).map(Comment::new);
    }

    private <T> Flux<T> wrapIntoIntervalOf3Seconds(DataSource<T> source) {
        return Flux.interval(Duration.ofSeconds(3))
                .flatMap(__ -> Mono.fromFuture(source.nextAsync()));
    }

    public Flux<Comment> getMessagesProcessor() {
        return messagesProcessor;
    }
}
