package com.ehem.kakaopay.institute.domain.vo;

import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Stream;

@Getter
public enum InstituteType {
    JOOTAEK("bnk01", "주택도시기금1"),
    KOOKMIN("bnk02", "국민은행"),
    WOORI("bnk03", "우리은행"),
    SHINHAN("bnk04", "신한은행"),
    CITY("bnk05", "한국시티은행"),
    HANA("bnk06", "하나은행"),
    NH("bnk07", "농협은행", "수협은행"),
    OEHWAN("bnk08", "외환은행"),
    EXTRA("bnk99", "기타은행");

    private final String code;
    private final String[] names;

    InstituteType(final String code, final String... names) {
        this.code = code;
        this.names = names;
    }

    public static InstituteType of(final String name) {
        return Stream.of(values())
                .filter(instituteType -> Arrays.asList(instituteType.getNames()).contains(name))
                .findFirst()
                .orElse(InstituteType.EXTRA);
    }
}
