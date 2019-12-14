package com.ehem.kakaopay.model.guarantee.domain.vo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YearTest {

    @ParameterizedTest
    @ValueSource(ints = {2000, 2001, 2005, 2006, 2007, 2010, 2013, 2016, 2019, 2020, 2025, 2030})
    void 캐싱된_객체_동일성_테스트(final int y) {
        Year year = Year.of(y);

        assertThat(year == Year.of(y)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1999})
    void 유효하지_않은_값_예외처리_테스트(final int y) {
        assertThrows(IllegalArgumentException.class, () -> Year.of(y));
    }

    @ParameterizedTest
    @ValueSource(ints = {2013, 2016, 2019, 2020, 2025, 2030, 2031, 2038})
    void getValue_테스트(final int y) {
        Year thisYear = Year.of(y);

        assertThat(thisYear.getValue()).isEqualTo(y);
    }
}