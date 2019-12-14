package com.ehem.kakaopay.parser.vo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.ehem.kakaopay.util.NumericUtils.removeCommaSeparatorAndQuotesFrom;

public class Record {
    private final String contents;

    public Record(final String contents) {
        this.contents = contents;
    }

    public List<String> getSplittedContents() {
        String result = removeCommaSeparatorAndQuotesFrom(contents);
        return Arrays.asList(result.split(","));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(contents, record.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contents);
    }
}
