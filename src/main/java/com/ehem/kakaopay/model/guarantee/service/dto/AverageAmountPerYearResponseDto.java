package com.ehem.kakaopay.model.guarantee.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AverageAmountPerYearResponseDto {
    private String institute;
    private YearValue min_amount;
    private YearValue max_amount;

    public AverageAmountPerYearResponseDto(final String institute,
                                           final AverageAmountPerYearResult minResult, final AverageAmountPerYearResult maxResult) {
        this.institute = institute;
        this.min_amount = new YearValue(minResult.getYear().getValue(), minResult.getAvg());
        this.max_amount = new YearValue(maxResult.getYear().getValue(), maxResult.getAvg());
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private class YearValue {
        private Integer year;
        private Double amount;

        YearValue(final Integer year, final Double amount) {
            this.year = year;
            this.amount = amount;
        }
    }
}
