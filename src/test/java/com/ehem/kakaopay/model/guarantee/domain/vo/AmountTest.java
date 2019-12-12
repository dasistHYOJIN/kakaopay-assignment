package com.ehem.kakaopay.model.guarantee.domain.vo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AmountTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 30, 100, 1230})
    void 객체_생성_테스트(final int amount) {
        assertThat(Amount.of(amount).getValue()).isEqualTo(amount);
    }

    @Test
    void 값_유효성_테스트() {
        assertThrows(IllegalArgumentException.class, () -> Amount.of(-1));
    }
}