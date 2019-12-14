package com.ehem.kakaopay.guarantee.domain.vo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MonthConverter implements AttributeConverter<Month, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final Month month) {
        return month.getNum();
    }

    @Override
    public Month convertToEntityAttribute(final Integer num) {
        return Month.of(num);
    }
}