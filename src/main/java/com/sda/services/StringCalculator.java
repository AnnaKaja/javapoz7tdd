package com.sda.services;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class StringCalculator {
    public int calculate(String text) {
        String textWithoutWhitespaces = StringUtils.deleteWhitespace(text);
        String[] elements = StringUtils.split(textWithoutWhitespaces, ";");
        elements = elements == null ? new String[0] : elements;
        return Arrays.stream(elements)
//                .mapToInt(new ToIntFunction<String>() {
//                    @Override
//                    public int applyAsInt(String value) {
//                        return Integer.valueOf(value);
//                    }
//                })
                .mapToInt(value -> Integer.valueOf(value)) //to robi to samo co wyzej zakomentowany kod
                .sum();
    }
}

