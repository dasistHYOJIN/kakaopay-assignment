package com.ehem.kakaopay.web.controller;

import com.ehem.kakaopay.web.message.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

class InstituteApiControllerTest extends RestTemplate {
    private static final String INSTITUTE_API = linkTo(InstituteApiController.class).toString();

    @Test
    void findAllInstitutes() throws IOException {
        // given
        saveGuaranteeDataFile();

        // when
        ApiResponse response = webTestClient.get()
                .uri(INSTITUTE_API + "/list")
                .exchange()
                .expectBody(ApiResponse.class)
                .returnResult()
                .getResponseBody();

        // then
        assertThat(response)
                .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.OK)
                .hasFieldOrPropertyWithValue("msg", "주택금융 공급 금융기관(은행) 목록");
        assertThat((List<String>) response.getData()).hasSize(9);
    }

}