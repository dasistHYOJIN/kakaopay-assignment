package com.ehem.kakaopay.model.guarantee.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TotalAmountAndInstitutePerYearResponseDto {
    private String year;
    private Long totalAmount;
    private Map<String, Long> detailAmount;

    public TotalAmountAndInstitutePerYearResponseDto(final int year, final Long totalAmount, final Map<String, Long> detailAmount) {
        this.year = year + "년";
        this.totalAmount = totalAmount;
        this.detailAmount = detailAmount;
    }
}
