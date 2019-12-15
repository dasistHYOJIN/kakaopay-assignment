package com.ehem.kakaopay.web.controller;

import com.ehem.kakaopay.model.guarantee.service.GuaranteeService;
import com.ehem.kakaopay.model.guarantee.service.dto.AverageAmountPerYearResponseDto;
import com.ehem.kakaopay.model.guarantee.service.dto.GuaranteeSavedResponseDto;
import com.ehem.kakaopay.model.guarantee.service.dto.MaxTotalAmountInstitutePerYearResult;
import com.ehem.kakaopay.model.guarantee.service.dto.TotalAmountAndInstitutePerYearResponseDto;
import com.ehem.kakaopay.web.message.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

        log.info("saveDataFile() >> {}개의 데이터 저장", savedGuarantees.size());

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "데이터베이스에 저장된 주택금융 공급현황", savedGuarantees));
    }

    @GetMapping("/max")
    public ResponseEntity<ApiResponse> getInstituteNamesByMaxTotalAmountPerYear() {
        List<MaxTotalAmountInstitutePerYearResult> results = guaranteeService.findInstituteNamesByMaxTotalAmountPerYear();

        log.info("getInstituteNamesByMaxTotalAmountPerYear() >> {}", results);

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "각 연도별 가장 높은 금액을 지원한 금융기관 데이터", results));
    }

    @GetMapping("/max/{year}")
    public ResponseEntity<ApiResponse> getInstituteNameByMaxTotalAmountPerYear(@PathVariable final int year) {
        MaxTotalAmountInstitutePerYearResult result = guaranteeService.findInstituteNameByMaxTotalAmountPerYear(year);

        log.info("getInstituteNameByMaxTotalAmountPerYear() >> {}", result);

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, String.format("%d년 가장 높은 금액을 지원한 금융기관 데이터", year), result));
    }

    @GetMapping("/sum")
    public ResponseEntity<ApiResponse> getTotalAmountPerYear() {
        List<TotalAmountAndInstitutePerYearResponseDto> results = guaranteeService.findTotalAmountPerYear();

        log.info("getInstituteNameByMaxTotalAmountPerYear() >> {}", results);

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "주택 금융 공급 현황 데이터", results));
    }

    @GetMapping("/avg/{instituteName}")
    public ResponseEntity<ApiResponse> getMinMaxAverageAmounts(@PathVariable final String instituteName) {
        AverageAmountPerYearResponseDto results = guaranteeService.findMinMaxAverageAmounts(instituteName);

        log.info("getMinMaxAverageAmounts() >> {}", results);

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, String.format("%s의 지원금액 평균 중에서 최소 및 최대 금액 데이터", instituteName), results));
    }
}
