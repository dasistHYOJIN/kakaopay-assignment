package com.ehem.kakaopay.util;

import com.ehem.kakaopay.parser.vo.Record;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileUtilsTest {

    @Test
    void 파일_읽기_테스트() throws IOException {
        // given
        MultipartFile file = new MockMultipartFile("file", "sajeongwaje.csv", "text/csv",
                ("연도,월,주택도시기금1)(억원),국민은행(억원),우리은행(억원),신한은행(억원),한국시티은행(억원),하나은행(억원),농협은행/수협은행(억원),외환은행(억원),기타은행(억원),,,,,,,\n" +
                        "2005,1,1019,846,82,95,30,157,57,80,99,,,,,,,\n" +
                        "2005,2,1144,864,91,97,35,168,36,111,114,,,,,,,\n" +
                        "2005,3,1828,1234,162,249,54,260,112,171,149,,,,,,,\n" +
                        "2005,4,2246,1176,209,167,66,291,101,220,111,,,,,,,\n" +
                        "2005,5,2106,1145,251,164,94,273,150,181,116,,,,,,,").getBytes());

        // when
        List<Record> records = FileUtils.readFile(file);

        // then
        assertThat(records).isEqualTo(Arrays.asList(
                new Record("연도,월,주택도시기금1)(억원),국민은행(억원),우리은행(억원),신한은행(억원),한국시티은행(억원),하나은행(억원),농협은행/수협은행(억원),외환은행(억원),기타은행(억원),,,,,,,"),
                new Record("2005,1,1019,846,82,95,30,157,57,80,99,,,,,,,"),
                new Record("2005,2,1144,864,91,97,35,168,36,111,114,,,,,,,"),
                new Record("2005,3,1828,1234,162,249,54,260,112,171,149,,,,,,,"),
                new Record("2005,4,2246,1176,209,167,66,291,101,220,111,,,,,,,"),
                new Record("2005,5,2106,1145,251,164,94,273,150,181,116,,,,,,,")));
    }

}