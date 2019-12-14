package com.ehem.kakaopay.web.controller;

import com.ehem.kakaopay.model.institute.service.InstituteService;
import com.ehem.kakaopay.model.institute.service.dto.InstituteSavedResponseDto;
import com.ehem.kakaopay.web.message.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/institute")
public class InstituteApiController {
    public static final String FIND_ALL_INSTITUTUES_SUCCESS_MESSAGE = "주택금융 공급 금융기관(은행) 목록";
    private final InstituteService instituteService;

    public InstituteApiController(final InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> findAllInstitutes() {
        List<InstituteSavedResponseDto> instituteSavedResponseDtos = instituteService.findAll();

        log.info("{}", instituteSavedResponseDtos);

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, FIND_ALL_INSTITUTUES_SUCCESS_MESSAGE, instituteSavedResponseDtos));
    }
}
