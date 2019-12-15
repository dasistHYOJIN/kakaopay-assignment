package com.ehem.kakaopay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.web.reactive.function.BodyInserters;

import java.io.File;
import java.io.IOException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplate {
    static final String GUARANTEE_API = linkTo(GuaranteeApiController.class).toString();

    static int testCount = 0;

    @Autowired
    WebTestClient webTestClient;

    ResponseSpec saveGuaranteeDataFile() throws IOException {
        testCount++;

        ClassPathResource classPathResource = new ClassPathResource("test.csv");
        File file = classPathResource.getFile();

        return webTestClient.post()
                .uri(GUARANTEE_API + "/new")
                .contentType(MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", new FileSystemResource(file)))
                .exchange();
    }
}
