package com.tt.reactive.source.io;


import com.tt.reactive.source.SentenceSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class BookSentenceExtractor implements SentenceSource {

    public static final String PATH_TO_BOOK = "src\\main\\resources\\Frankenstein or The Modern Prometheus.txt";
    private List<String> lines = new ArrayList<>();
    private Random random = new Random();
    private int size;

    public BookSentenceExtractor() {
        this(Path.of(PATH_TO_BOOK));
    }

    public BookSentenceExtractor(Path bookPath) {
        try {
            Files.lines(bookPath, StandardCharsets.UTF_8)
                    .forEach(lines::add);
        } catch (IOException e) {
            //todo logger
            System.err.println("Cannot read file content, application launching failed!");

            //todo degrade gracefully
            System.exit(-1);
        }
        size = lines.size();
    }

    @Override
    public CompletableFuture<String> nextAsync() {
        return CompletableFuture.completedFuture(lines.get(random.nextInt(size)));
    }

    @Override
    public String next() {
        return lines.get(random.nextInt(size));
    }
}
