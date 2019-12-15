package com.ehem.kakaopay.model.guarantee.domain.vo;

import com.ehem.kakaopay.model.guarantee.exception.InvalidFieldValueException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class Amount {
    private static final int MIN_VALUE = 0;

    private int value;

    private Amount(final int value) {
        this.value = validateAmount(value);
    }

    public static Amount of(final int value) {
        return new Amount(value);
    }

    private int validateAmount(final int value) {
        if (value < MIN_VALUE) {
            throw new InvalidFieldValueException("신용보증 금액은 0 이상의 자연수여야 합니다.");
        }

        return value;
    }
}
