package com.ehem.kakaopay.model.institute.domain.vo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class InstituteTypeConverter implements AttributeConverter<InstituteType, String> {

    @Override
    public String convertToDatabaseColumn(final InstituteType instituteType) {
        return instituteType.getName();
    }

    @Override
    public InstituteType convertToEntityAttribute(final String name) {
        return InstituteType.ofName(name);
    }
}
