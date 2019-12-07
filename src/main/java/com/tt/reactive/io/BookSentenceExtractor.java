package com.tt.reactive.io;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookSentenceExtractor implements SentenceSource {

    private List<String> lines = new ArrayList<>();
    private Random random = new Random();
    private int size;

    public BookSentenceExtractor() {
        this(Path.of("src\\main\\resources\\Frankenstein or The Modern Prometheus.txt"));
    }

    public BookSentenceExtractor(Path bookPath) {
        try {
            Files.lines(bookPath, StandardCharsets.UTF_8)
                    .forEach(lines::add);
        } catch (IOException e) {
            System.err.println("Cannot read file content, application launching failed!");
            System.exit(-1);
        }
        size = lines.size();
    }

    public String nextSentence() {
        return lines.get(random.nextInt(size));
    }
}
