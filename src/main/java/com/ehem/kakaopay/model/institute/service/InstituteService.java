package com.ehem.kakaopay.model.institute.service;

import com.ehem.kakaopay.model.guarantee.domain.Guarantee;
import com.ehem.kakaopay.model.institute.domain.Institute;
import com.ehem.kakaopay.model.institute.repository.InstituteRepository;
import com.ehem.kakaopay.model.institute.service.dto.InstituteSavedResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstituteService {
    private final InstituteRepository instituteRepository;

    public InstituteService(final InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    public List<InstituteSavedResponseDto> saveAll(final List<Guarantee> guarantees) {
        List<Institute> institutes = guarantees.stream().map(Guarantee::getInstitute).collect(Collectors.toList());

        return instituteRepository.saveAll(institutes).stream()
                .map(this::toSavedResponseDto)
                .collect(Collectors.toList());
    }

    public List<InstituteSavedResponseDto> findAll() {
        return instituteRepository.findAll().stream()
                .map(this::toSavedResponseDto)
                .collect(Collectors.toList());
    }

    private InstituteSavedResponseDto toSavedResponseDto(final Institute institute) {
        return new InstituteSavedResponseDto(
                institute.getName(),
                institute.getCode());
    }
}
