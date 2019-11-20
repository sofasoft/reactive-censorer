package com.tt.reactive.gateway;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsultProcessorTest {

    @Test
    void getInsultSimpleExample() {
        List<String> list = new ArrayList<>();

        //todo mock gateway
        InsultProcessor insultProcessor = new InsultProcessor();

        insultProcessor.getInsult()
                .doOnNext(string -> {
                    assert string != null;
                    assert !string.isBlank();
                    list.add(string);
                })
                .doOnNext(string -> {
                    assert string != null;
                    assert !string.isBlank();
                    assert !list.get(0).equals(string);
                });
    }
}