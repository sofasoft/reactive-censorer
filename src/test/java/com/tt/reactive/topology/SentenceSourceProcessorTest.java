package com.tt.reactive.topology;

import com.tt.reactive.source.io.BookSentenceExtractor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import java.time.Duration;

class SentenceSourceProcessorTest {

    @Test
    void getSentenceSource() {
        BookSentenceExtractor mock = Mockito.mock(BookSentenceExtractor.class);
        SentenceSourceProcessor sentenceSource = new SentenceSourceProcessor(mock, Duration.ZERO);

        String expectedSentence1 = "First sentence";
        String expectedSentence2 = "Second Sentence";

        Mockito.when(mock.next())
                .thenReturn(expectedSentence1)
                .thenReturn(expectedSentence2);

        StepVerifier.create(sentenceSource.getSentenceSource().take(2))
                .expectNext(expectedSentence1)
                .expectNext(expectedSentence2)
                .verifyComplete();
    }
}