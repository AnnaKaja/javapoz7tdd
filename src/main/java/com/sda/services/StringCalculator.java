package com.sda.services;

public class StringCalculator {
    public int calculate(String text) {
        String[] split = text.split(";");
        return Integer.valueOf(split[0]) + Integer.valueOf(split[1]);
    }
}

