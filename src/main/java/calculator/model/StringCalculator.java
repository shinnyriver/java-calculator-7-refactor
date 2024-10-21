package calculator.model;

import java.util.Arrays;

public class StringCalculator {
    private final String str;

    public StringCalculator(String str) {
        this.str = str;
    }

    // 기본 구분자
    private String delimiter = ",:";
    // 특수문자 이스케이프를 위한 특수문자 문자열
    private static final String specialDelimiter = ".*+?^${}()|[]\\";

    private int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cannot change string to integer");
        }
    }

    private void checkNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Negative numbers are forbidden : " + number);
        }
    }

    private void checkNull(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Wrong usage of delimiter");
        }
    }

    private String escapeSpecialDelimiter(String customDelimiter) {
        StringBuilder escapedDelimiter = new StringBuilder();
        for (char c : customDelimiter.toCharArray()) {
            if (specialDelimiter.indexOf(c) != -1) {
                escapedDelimiter.append("\\");
            }
            escapedDelimiter.append(c);
        }
        return escapedDelimiter.toString();
    }

    private String extractNumbersWithCustomDelimiter() {
        int index = str.indexOf("\\n");
        if (index == -1) {
            throw new IllegalArgumentException("Missing \\n after custom delimiter");
        }

        String customDelimiter = str.substring(2, index);
        if (customDelimiter.isEmpty()) {
            throw new IllegalArgumentException("Custom delimiter cannot be empty");
        }
        delimiter += escapeSpecialDelimiter(customDelimiter);
        return str.substring(index + 2);
    }

    public int add() {
        String numbers = str;

        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        if (numbers.startsWith("//")) {
            numbers = extractNumbersWithCustomDelimiter();
        }

        return Arrays.stream(numbers.split("[" + delimiter + "]", -1))
                .map(String::trim)
                .peek(this::checkNull)
                .mapToInt(this::parseInt)
                .peek(this::checkNegative)
                .sum();
    }
}
