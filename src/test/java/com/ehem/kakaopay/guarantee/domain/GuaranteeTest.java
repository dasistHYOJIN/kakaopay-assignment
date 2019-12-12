package com.ehem.kakaopay.guarantee.domain;

import com.ehem.kakaopay.guarantee.domain.vo.Amount;
import com.ehem.kakaopay.institute.domain.Institute;
import com.ehem.kakaopay.institute.domain.vo.InstituteType;
import com.ehem.kakaopay.guarantee.domain.vo.Month;
import com.ehem.kakaopay.guarantee.domain.vo.Year;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GuaranteeTest {

    @Test
    void 신용보증_객체_초기화_테스트() {
        Guarantee guarantee = Guarantee.builder()
                .year(Year.of(2019))
                .month(Month.of(12))
                .institute(new Institute(InstituteType.WOORI))
                .amount(Amount.of(79))
                .build();

        assertThat(guarantee.getYear()).isEqualTo(Year.of(2019));
        assertThat(guarantee.getMonth()).isEqualTo(Month.of(12));
        assertThat(guarantee.getInstitute()).isEqualTo(new Institute(InstituteType.WOORI));
        assertThat(guarantee.getAmount()).isEqualTo(Amount.of(79));
    }
}