package com.ehem.kakaopay.model.institute.domain.vo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InstituteTypeTest {

    @Test
    void 은행이름에_맞는_객체를_반환하는지_테스트() {
        assertThat(InstituteType.ofName("국민은행")).isEqualByComparingTo(InstituteType.KOOKMIN);
        assertThat(InstituteType.ofName("농협은행/수협은행")).isEqualByComparingTo(InstituteType.NH_SH);
        assertThat(InstituteType.ofName("아무개은행")).isEqualByComparingTo(InstituteType.EXTRA);
    }
}