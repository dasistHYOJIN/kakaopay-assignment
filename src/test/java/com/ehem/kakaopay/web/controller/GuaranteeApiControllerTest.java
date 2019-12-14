package com.ehem.kakaopay.web.controller;

import com.ehem.kakaopay.web.message.ApiResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GuaranteeApiControllerTest extends RestTemplate {

    @Test
    void saveDataFile() throws IOException {
        ApiResponse apiResponses = saveGuaranteeDataFile()
                .expectBody(ApiResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat((List<ApiResponse>) apiResponses.getData()).hasSize(90);
    }
}