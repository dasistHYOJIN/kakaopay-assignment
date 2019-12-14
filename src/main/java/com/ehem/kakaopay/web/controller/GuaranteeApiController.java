package com.ehem.kakaopay.web.controller;

import com.ehem.kakaopay.model.guarantee.service.GuaranteeService;
import com.ehem.kakaopay.model.guarantee.service.dto.GuaranteeSavedResponseDto;
import com.ehem.kakaopay.web.message.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/guarantee")
public class GuaranteeApiController {
    private final GuaranteeService guaranteeService;

    public GuaranteeApiController(final GuaranteeService guaranteeService) {
        this.guaranteeService = guaranteeService;
    }

    @PostMapping("/new")
    public ResponseEntity<ApiResponse> saveDataFile(@RequestParam(name = "file") final MultipartFile file) {
        List<GuaranteeSavedResponseDto> savedGuarantees = guaranteeService.save(file);

        log.info("{}", savedGuarantees);

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "데이터베이스에 저장된 주택금융 공급현황", savedGuarantees));
    }
}
