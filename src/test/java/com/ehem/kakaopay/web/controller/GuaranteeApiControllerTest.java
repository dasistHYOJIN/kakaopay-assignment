package com.ehem.kakaopay.web.controller;

import com.ehem.kakaopay.web.message.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @Test
    void getInstituteNamesByMaxTotalAmountPerYear() throws IOException {
        // given
        saveGuaranteeDataFile();

        // when
        ApiResponse response = webTestClient.get()
                .uri(GUARANTEE_API + "/max")
                .exchange()
                .expectBody(ApiResponse.class)
                .returnResult()
                .getResponseBody();

        // then
        assertThat(response)
                .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.OK)
                .hasFieldOrPropertyWithValue("msg", "각 연도별 가장 높은 금액을 지원한 금융기관 데이터");
        assertThat((List<String>) response.getData()).hasSize(2);
    }

    @Test
    void getInstituteNameByMaxTotalAmountPerYear() throws IOException {
        // given
        saveGuaranteeDataFile();

        // when
        ApiResponse response = webTestClient.get()
                .uri(GUARANTEE_API + "/max/2005")
                .exchange()
                .expectBody(ApiResponse.class)
                .returnResult()
                .getResponseBody();

        // then
        assertThat(response)
                .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.OK)
                .hasFieldOrPropertyWithValue("msg", "2005년 가장 높은 금액을 지원한 금융기관 데이터");
        assertThat((Map<String, Object>) response.getData())
                .containsEntry("year", 2005)
                .containsEntry("maxAmount", 8343 * testCount)
                .containsEntry("instituteName", "주택도시기금");
    }

    @Test
    void 존재하지_않는_연도의_최대지원금액_금융기관_테스트() throws IOException {
        // given
        saveGuaranteeDataFile();

        // when
        ApiResponse response = webTestClient.get()
                .uri(GUARANTEE_API + "/max/2090")
                .exchange()
                .expectBody(ApiResponse.class)
                .returnResult()
                .getResponseBody();

        // then
        assertThat(response)
                .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.UNAUTHORIZED)
                .hasFieldOrPropertyWithValue("msg", "2090년에 해당하는 데이터가 존재하지 않습니다.")
                .hasFieldOrPropertyWithValue("data", null);
    }
}