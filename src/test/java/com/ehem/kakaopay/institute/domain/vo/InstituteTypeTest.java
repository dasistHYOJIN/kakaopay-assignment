package com.ehem.kakaopay.institute.domain.vo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InstituteTypeTest {

    @Test
    void 은행이름에_맞는_객체를_반환하는지_테스트() {
        assertThat(InstituteType.of("국민은행")).isEqualByComparingTo(InstituteType.KOOKMIN);
        assertThat(InstituteType.of("농협은행")).isEqualByComparingTo(InstituteType.NH);
        assertThat(InstituteType.of("수협은행")).isEqualByComparingTo(InstituteType.NH);
        assertThat(InstituteType.of("아무개은행")).isEqualByComparingTo(InstituteType.EXTRA);
    }
}