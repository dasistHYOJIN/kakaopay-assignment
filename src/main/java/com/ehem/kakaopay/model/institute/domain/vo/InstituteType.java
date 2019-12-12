package com.ehem.kakaopay.model.institute.domain.vo;

import lombok.Getter;

import javax.persistence.Convert;
import java.util.stream.Stream;

@Getter
@Convert(converter = InstituteTypeConverter.class, attributeName = "name")
public enum InstituteType {
    JOOTAEK("bnk01", "주택도시기금1"),
    KOOKMIN("bnk02", "국민은행"),
    WOORI("bnk03", "우리은행"),
    SHINHAN("bnk04", "신한은행"),
    CITY("bnk05", "한국시티은행"),
    HANA("bnk06", "하나은행"),
    NH("bnk07", "농협은행/수협은행"),
    OEHWAN("bnk08", "외환은행"),
    EXTRA("bnk99", "기타은행");

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
