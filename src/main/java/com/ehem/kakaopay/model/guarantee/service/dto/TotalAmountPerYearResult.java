package com.ehem.kakaopay.model.guarantee.service.dto;

import com.ehem.kakaopay.model.guarantee.domain.vo.Year;
import com.ehem.kakaopay.model.institute.domain.Institute;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class TotalAmountPerYearResult {
    private Year year;
    private Institute institute;
    private long sum;

    public TotalAmountPerYearResult(final Year year, final Institute institute, final long sum) {
        this.year = year;
        this.institute = institute;
        this.sum = sum;
    }
}
