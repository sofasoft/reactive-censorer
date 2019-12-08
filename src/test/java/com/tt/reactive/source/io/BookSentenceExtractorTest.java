package com.tt.reactive.source.io;

import com.tt.reactive.source.SentenceSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//todo another text source test
class BookSentenceExtractorTest {

    @Test
    void nextSentence() {
        SentenceSource extractor = new BookSentenceExtractor();
        String sentence1 = extractor.nextSentence();

        Assertions.assertNotNull(sentence1);
        Assertions.assertFalse(sentence1.isBlank());

        String sentence2 = extractor.nextSentence();

        Assertions.assertNotNull(sentence2);
        Assertions.assertFalse(sentence2.isBlank());

        Assertions.assertNotEquals(sentence1, sentence2);
    }
}