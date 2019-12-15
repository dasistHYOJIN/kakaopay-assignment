package com.ehem.kakaopay.model.guarantee.service;

import com.ehem.kakaopay.model.guarantee.FileValidator;
import com.ehem.kakaopay.model.guarantee.domain.Guarantee;
import com.ehem.kakaopay.model.guarantee.domain.vo.Year;
import com.ehem.kakaopay.model.guarantee.exception.NoSuchDataException;
import com.ehem.kakaopay.model.guarantee.repository.GuaranteeRepository;
import com.ehem.kakaopay.model.guarantee.service.dto.GuaranteeSavedResponseDto;
import com.ehem.kakaopay.model.guarantee.service.dto.MaxTotalAmountInstitutePerYearResult;
import com.ehem.kakaopay.model.institute.service.InstituteService;
import com.ehem.kakaopay.parser.GuaranteeParser;
import com.ehem.kakaopay.parser.vo.Record;
import com.ehem.kakaopay.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
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

    public List<MaxTotalAmountInstitutePerYearResult> findInstituteNamesByMaxTotalAmountPerYear() {
        return guaranteeRepository.findAllInstituteNamesByYearAndMaxTotalAmount().stream()
                .map(MaxTotalAmountInstitutePerYearResult::new)
                .collect(Collectors.toList());
    }

    public MaxTotalAmountInstitutePerYearResult findInstituteNameByMaxTotalAmountPerYear(final int year) {
        if (hasNoData(guaranteeRepository.countByYear(Year.of(year)))) {
            throw new NoSuchDataException(String.format("%d년에 해당하는 데이터가 존재하지 않습니다.", year));
        }

        Object[] result = guaranteeRepository.findInstituteNameByYearAndMaxTotalAmount(year).get(0);
        return new MaxTotalAmountInstitutePerYearResult(result);
    }

    private boolean hasNoData(final int count) {
        return count == 0;
    }

    private GuaranteeSavedResponseDto toSavedResponseDto(final Guarantee guarantee) {
        return new GuaranteeSavedResponseDto(
                guarantee.getYear().getValue(),
                guarantee.getMonth().getNum(),
                guarantee.getAmount().getValue(),
                guarantee.getInstitute().getInstituteType().getName());
    }
}
