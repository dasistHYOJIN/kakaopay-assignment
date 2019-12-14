package com.ehem.kakaopay.parser.vo;

import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

import static com.ehem.kakaopay.util.NumericUtils.removeCommaSeparatorAndQuotesFrom;

@EqualsAndHashCode
public class Record {
    private final String contents;

    public Record(final String contents) {
        this.contents = contents;
    }

    public List<String> getSplittedContents() {
        String result = removeCommaSeparatorAndQuotesFrom(contents);
        return Arrays.asList(result.split(","));
    }
}
