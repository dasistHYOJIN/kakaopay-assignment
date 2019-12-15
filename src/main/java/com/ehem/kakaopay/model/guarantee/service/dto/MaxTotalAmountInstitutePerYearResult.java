package com.ehem.kakaopay.model.guarantee.service.dto;

import com.ehem.kakaopay.model.institute.domain.vo.InstituteType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class MaxTotalAmountInstitutePerYearResult {
    private static final int YEAR_INDEX = 0;
    private static final int MAX_AMOUNT_INDEX = 1;
    private static final int INSTITUTE_NAME_INDEX = 2;

    private Integer year;
    private BigInteger maxAmount;
    private String instituteName;

    public MaxTotalAmountInstitutePerYearResult(final Object[] objects) {
        this.year = (Integer) objects[YEAR_INDEX];
        this.maxAmount = (BigInteger) objects[MAX_AMOUNT_INDEX];
        this.instituteName = getInstituteName(objects[INSTITUTE_NAME_INDEX]);
    }

    private String getInstituteName(final Object object) {
        return InstituteType.valueOf((String) object).getName();
    }
}
