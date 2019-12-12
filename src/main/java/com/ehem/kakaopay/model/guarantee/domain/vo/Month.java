package com.ehem.kakaopay.model.guarantee.domain.vo;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Month {
    JAN(1), FEB(2), MAR(3), APR(4),
    MAY(5), JUN(6), JUL(7), AUG(8),
    SEP(9), OCT(10), NOV(11), DEC(12);

    private final int num;

    Month(final int num) {
        this.num = num;
    }

    public static Month of(final int num) {
        return Stream.of(values())
                .filter(month -> month.getNum() == num)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d월은 존재하지 않는 달입니다.", num)));
    }
}
