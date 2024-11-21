package com.poly.util;

import java.text.Normalizer;

public class StringUtils {
    public static String removeAccentsAndSpaces(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // Loại bỏ dấu tiếng Việt
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String withoutAccents = normalized.replaceAll("\\p{M}", "");

        // Loại bỏ khoảng cách
        return withoutAccents.replaceAll("\\s+", "");
    }
}
