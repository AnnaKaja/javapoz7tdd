package com.sda.services;

import org.apache.commons.lang3.StringUtils;

public class StringCalculator {
    public int calculate(String text) {
        if (StringUtils.isBlank(text)) {
            return 0;
        }
        String[] split = text.split(";");
        return Integer.valueOf(split[0]) + Integer.valueOf(split[1]);
    }
}

