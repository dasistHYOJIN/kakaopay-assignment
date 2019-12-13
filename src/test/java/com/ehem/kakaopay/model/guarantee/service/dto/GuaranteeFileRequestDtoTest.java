package com.ehem.kakaopay.model.guarantee.service.dto;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GuaranteeFileRequestDtoTest {

    @Test
    void Dto_유효성_테스트() {
        // given
        MultipartFile file = new MockMultipartFile("file", "sajeongwaje.json",
                "text/json", "{error: 'error!'}".getBytes());

        // then
        assertThrows(IllegalArgumentException.class, () -> new GuaranteeFileRequestDto(file));
    }
}