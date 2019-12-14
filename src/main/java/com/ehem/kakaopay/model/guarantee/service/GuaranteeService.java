package com.ehem.kakaopay.model.guarantee.service;

import com.ehem.kakaopay.model.guarantee.FileValidator;
import com.ehem.kakaopay.model.guarantee.domain.Guarantee;
import com.ehem.kakaopay.model.guarantee.exception.IllegalFileFormatException;
import com.ehem.kakaopay.model.guarantee.repository.GuaranteeRepository;
import com.ehem.kakaopay.model.guarantee.service.dto.GuaranteeSavedResponseDto;
import com.ehem.kakaopay.model.institute.service.InstituteService;
import com.ehem.kakaopay.util.FileUtils;
import com.ehem.kakaopay.parser.GuaranteeParser;
import com.ehem.kakaopay.parser.vo.Record;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GuaranteeService {
    private final GuaranteeRepository guaranteeRepository;
    private final InstituteService instituteService;
    private final FileValidator fileValidator;

    public GuaranteeService(final GuaranteeRepository guaranteeRepository, final InstituteService instituteService, final FileValidator fileValidator) {
        this.guaranteeRepository = guaranteeRepository;
        this.instituteService = instituteService;
        this.fileValidator = fileValidator;
    }

    public List<GuaranteeSavedResponseDto> save(final MultipartFile file) {
        List<Record> records = readFile(fileValidator.validate(file));
        List<Guarantee> guarantees = GuaranteeParser.parseToGuarantees(records);

        instituteService.saveAll(guarantees);

        return guaranteeRepository.saveAll(guarantees).stream()
                .map(this::toSavedResponseDto)
                .collect(Collectors.toList());
    }

    private List<Record> readFile(final MultipartFile file) {
        try {
            return FileUtils.readFile(file);
        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 읽는 중에 오류가 발생하였습니다.");
        }
    }

    private GuaranteeSavedResponseDto toSavedResponseDto(final Guarantee guarantee) {
        return new GuaranteeSavedResponseDto(
                guarantee.getYear().getValue(),
                guarantee.getMonth().getNum(),
                guarantee.getAmount().getValue(),
                guarantee.getInstitute().getInstituteType().getName());
    }
}
