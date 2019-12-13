package com.ehem.kakaopay.util;

import com.ehem.kakaopay.model.guarantee.domain.Guarantee;
import com.ehem.kakaopay.model.guarantee.domain.vo.Amount;
import com.ehem.kakaopay.model.guarantee.domain.vo.Month;
import com.ehem.kakaopay.model.guarantee.domain.vo.Year;
import com.ehem.kakaopay.model.institute.domain.Institute;
import com.ehem.kakaopay.model.institute.domain.vo.InstituteType;
import com.ehem.kakaopay.util.vo.Record;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GuaranteeParser {
    private static final String INSTITUTE_REGEX = "[\\(0-9]";
    private static final int HEADER_INDEX = 0;
    private static final int INSTITUTE_NAME_INDEX = 0;
    private static final int INSTITUTE_INDEX = 2;

    public static List<Guarantee> parseToGuarantees(final List<Record> records) {
        List<String> header = extractHeader(records);

        return records.stream()
                .map(record -> parseRecordToGuarantees(header, record))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<Guarantee> parseRecordToGuarantees(final List<String> header, final Record record) {
        List<String> columns = record.split();

        return IntStream.range(INSTITUTE_INDEX, columns.size())
                .mapToObj(index -> Guarantee.builder()
                        .year(Year.of(Integer.parseInt(columns.get(0))))
                        .month(Month.of(Integer.parseInt(columns.get(1))))
                        .institute(new Institute(InstituteType.ofName(header.get(index))))
                        .amount(Amount.of(Integer.parseInt(columns.get(index))))
                        .build())
                .collect(Collectors.toList());
    }

    private static List<String> extractHeader(final List<Record> records) {
        List<String> header = records.remove(HEADER_INDEX).split();

        return header.stream()
                .map(GuaranteeParser::extractNameIfInstitute)
                .collect(Collectors.toList());
    }

    private static String extractNameIfInstitute(final String column) {
        return column.split(INSTITUTE_REGEX, 0)[INSTITUTE_NAME_INDEX];
    }
}