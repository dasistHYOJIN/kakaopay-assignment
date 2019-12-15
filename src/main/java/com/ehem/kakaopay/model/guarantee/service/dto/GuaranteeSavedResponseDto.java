package com.ehem.kakaopay.model.guarantee.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GuaranteeSavedResponseDto {
    private int year;
    private int month;
    private int amount;
    private String institute;

    public GuaranteeSavedResponseDto(final int year, final int month, final int amount, final String institute) {
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.institute = institute;
    }
}
