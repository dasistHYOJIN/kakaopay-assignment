package com.ehem.kakaopay.model.institute.domain.vo;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum InstituteType {
    JOOTAEK("ins01", "주택도시기금"),
    KOOKMIN("ins02", "국민은행"),
    WOORI("ins03", "우리은행"),
    SHINHAN("ins04", "신한은행"),
    CITY("ins05", "한국시티은행"),
    HANA("ins06", "하나은행"),
    NH("ins07", "농협은행/수협은행"),
    OEHWAN("ins08", "외환은행"),
    EXTRA("ins99", "기타은행");

    private final String code;
    private final String name;

    InstituteType(final String code, final String name) {
        this.code = code;
        this.name = name;
    }

    public static InstituteType ofName(final String name) {
        return Stream.of(values())
                .filter(instituteType -> instituteType.getName().equals(name))
                .findFirst()
                .orElse(InstituteType.EXTRA);
    }

    public static InstituteType ofCode(final String code) {
        return Stream.of(values())
                .filter(instituteType -> instituteType.getCode().equals(code))
                .findFirst()
                .orElse(InstituteType.EXTRA);
    }
}
