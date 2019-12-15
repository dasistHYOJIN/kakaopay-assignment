package com.ehem.kakaopay.model.guarantee.service.dto;

import com.ehem.kakaopay.model.guarantee.domain.vo.Year;
import com.ehem.kakaopay.model.institute.domain.Institute;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class AverageAmountPerYearResult {
    private Integer avg;
    private Year year;
    private Institute institute;

    public AverageAmountPerYearResult(final Double avg, final Year year, final Institute institute) {
        this.avg = avg.intValue();
        this.year = year;
        this.institute = institute;
    }
}
