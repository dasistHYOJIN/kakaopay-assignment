package com.ehem.kakaopay.model.institute.service;

import com.ehem.kakaopay.model.institute.domain.Institute;
import com.ehem.kakaopay.model.institute.domain.vo.InstituteType;
import com.ehem.kakaopay.model.institute.repository.InstituteRepository;
import com.ehem.kakaopay.model.institute.service.dto.InstituteSavedResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class InstituteServiceTest {

    @InjectMocks
    private InstituteService instituteService;

    @Mock
    private InstituteRepository instituteRepository;

    @Test
    void DB에_데이터가_없는_경우_모든_금융기관_받아오기_테스트() {
        // given
        given(instituteRepository.findAll()).willReturn(Collections.emptyList());

        // when
        List<InstituteSavedResponseDto> instituteSavedResponseDtos = instituteService.findAll();

        // then
        assertThat(instituteSavedResponseDtos).hasSize(0).isEmpty();
    }

    @Test
    void 모든_금융기관_받아오기_테스트() {
        // given
        given(instituteRepository.findAll()).willReturn(Arrays.asList(
                new Institute(InstituteType.JOOTAEK),
                new Institute(InstituteType.KOOKMIN),
                new Institute(InstituteType.WOORI),
                new Institute(InstituteType.SHINHAN),
                new Institute(InstituteType.CITY),
                new Institute(InstituteType.EXTRA)));

        // when
        List<InstituteSavedResponseDto> instituteSavedResponseDtos = instituteService.findAll();

        // then
        assertThat(instituteSavedResponseDtos)
                .hasSize(6)
                .containsExactly(
                        new InstituteSavedResponseDto(InstituteType.JOOTAEK.getName(), InstituteType.JOOTAEK.getCode()),
                        new InstituteSavedResponseDto(InstituteType.KOOKMIN.getName(), InstituteType.KOOKMIN.getCode()),
                        new InstituteSavedResponseDto(InstituteType.WOORI.getName(), InstituteType.WOORI.getCode()),
                        new InstituteSavedResponseDto(InstituteType.SHINHAN.getName(), InstituteType.SHINHAN.getCode()),
                        new InstituteSavedResponseDto(InstituteType.CITY.getName(), InstituteType.CITY.getCode()),
                        new InstituteSavedResponseDto(InstituteType.EXTRA.getName(), InstituteType.EXTRA.getCode())
                );
    }
}