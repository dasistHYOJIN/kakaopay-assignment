package com.ehem.kakaopay.web.controller;

import com.ehem.kakaopay.web.message.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GuaranteeApiControllerTest {
    private static final String GUARANTEE_API = linkTo(GuaranteeApiController.class).toString();

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void name() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("test.csv");
        File file = classPathResource.getFile();

        ApiResponse apiResponses = webTestClient.post()
                .uri(GUARANTEE_API + "/new")
                .contentType(MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", new FileSystemResource(file)))
                .exchange()
                .expectBody(ApiResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat((List<ApiResponse>) apiResponses.getData()).hasSize(90);
    }
}