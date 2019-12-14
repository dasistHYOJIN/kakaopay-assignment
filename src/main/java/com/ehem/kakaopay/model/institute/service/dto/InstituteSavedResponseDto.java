package com.ehem.kakaopay.model.institute.service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class InstituteSavedResponseDto {
    private String name;
    private String code;

    public InstituteSavedResponseDto(final String name, final String code) {
        this.name = name;
        this.code = code;
    }
}
