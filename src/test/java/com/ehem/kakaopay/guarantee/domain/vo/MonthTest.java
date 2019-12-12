package com.ehem.kakaopay.guarantee.domain.vo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MonthTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void 객체_생성_테스트(final int month) {
        assertThat(Month.of(month).getNum()).isEqualTo(month);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 13, 19})
    void 존재하지_않는_달의_객체를_가져오는_경우_예외처리_테스트(final int month) {
        assertThrows(IllegalArgumentException.class, () -> Month.of(month));
    }
}