package com.ehem.kakaopay.parser.vo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class RecordTest {

    @Test
    void contents_구분자단위로_split_테스트() {
        Record record = new Record("1,2,3,,,,,");

        assertThat(record.getSplittedContents()).isEqualTo(Arrays.asList("1", "2", "3"));
    }
}