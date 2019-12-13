package com.ehem.kakaopay.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericUtils {
    private static final String COMMA_SEPARATOR_REGEX = "\"[\\d]+([,]?[\\d]{3})*([.]{1}[\\d]+)*\"";
    private static final String COMMA_OR_QUOTES_REGEX = "[,|\"]";
    private static final Pattern COMMA_SEPARATOR_PATTERN = Pattern.compile(COMMA_SEPARATOR_REGEX);
    private static final String EMPTY_STRING = "";

    public static String removeCommaSeparatorAndQuotesFrom(final String value) {
        Matcher matcher = COMMA_SEPARATOR_PATTERN.matcher(value);
        String numbers = value;

        while (matcher.find()) {
            String rawNumber = matcher.group();
            String parsedNumber = rawNumber.replaceAll(COMMA_OR_QUOTES_REGEX, EMPTY_STRING);

            numbers = numbers.replaceAll(rawNumber, parsedNumber);
        }

        return numbers;
    }
}
