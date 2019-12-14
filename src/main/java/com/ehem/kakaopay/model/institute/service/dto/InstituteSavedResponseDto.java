package com.ehem.kakaopay.model.institute.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InstituteSavedResponseDto {
    private String name;
    private String code;

    public InstituteSavedResponseDto(final String name, final String code) {
        this.name = name;
        this.code = code;
    }
}
