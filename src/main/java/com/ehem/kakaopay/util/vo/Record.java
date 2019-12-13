package com.ehem.kakaopay.util.vo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Record {
    private final String contents;

    public Record(final String contents) {
        this.contents = contents;
    }

    public List<String> split() {
        return Arrays.asList(contents.split(",", 0));
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
