package com.ehem.kakaopay.model.guarantee.domain.vo;

import com.ehem.kakaopay.model.guarantee.exception.InvalidFieldValueException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class Year {
    private static final int MIN_VALUE = 2000;

    private static final Map<Integer, Year> years = new HashMap<>();

    static {
        IntStream.rangeClosed(MIN_VALUE, 2030).forEach(year -> years.put(year, Year.of(year)));
    }

    private int value;

    private Year(final int value) {
        this.value = validateYear(value);
    }

    public static Year of(final int value) {
        return years.getOrDefault(value, new Year(value));
    }

    private int validateYear(final int value) {
        if (value < MIN_VALUE) {
            throw new InvalidFieldValueException(String.format("연도는 %d 이상의 자연수여야 합니다.", MIN_VALUE));
        }

        return value;
    }
}
