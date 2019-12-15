package com.ehem.kakaopay.model.guarantee.service.dto;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AverageAmountPerYearResponseDto {
    private String institute;
    private YearValue minAmount;
    private YearValue maxAmount;

    public AverageAmountPerYearResponseDto(final String institute,
                                           final AverageAmountPerYearResult minResult, final AverageAmountPerYearResult maxResult) {
        this.institute = institute;
        this.minAmount = new YearValue(minResult.getYear().getValue(), minResult.getAvg());
        this.maxAmount = new YearValue(maxResult.getYear().getValue(), maxResult.getAvg());
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @EqualsAndHashCode
    public class YearValue {
        private Integer year;
        private Double amount;

        YearValue(final Integer year, final Double amount) {
            this.year = year;
            this.amount = amount;
        }
    }
}
