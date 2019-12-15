package com.ehem.kakaopay.model.guarantee.service;

import com.ehem.kakaopay.model.guarantee.domain.vo.Year;
import com.ehem.kakaopay.model.guarantee.exception.NoSuchDataException;
import com.ehem.kakaopay.model.guarantee.repository.GuaranteeRepository;
import com.ehem.kakaopay.model.guarantee.service.dto.MaxTotalAmountInstitutePerYearResult;
import com.ehem.kakaopay.model.institute.domain.vo.InstituteType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class GuaranteeServiceTest {

    @InjectMocks
    private GuaranteeService guaranteeService;

    @Mock
    private GuaranteeRepository guaranteeRepository;


    @Test
    void 각_연도의_데이터_출력_테스트() {
        // given
        int year1 = 2020;
        BigInteger maxAmount1 = BigInteger.valueOf(12345);
        String instituteName1 = InstituteType.JOOTAEK.name();

        int year2 = 2021;
        BigInteger maxAmount2 = BigInteger.valueOf(145);
        String instituteName2 = InstituteType.WOORI.name();

        Object[] data1 = {year1, maxAmount1, instituteName1};
        Object[] data2 = {year2, maxAmount2, instituteName2};
        List<Object[]> data = Arrays.asList(data1, data2);

        given(guaranteeRepository.findAllInstitutesByYearAndMaxTotalAmount()).willReturn(data);

        // when
        List<MaxTotalAmountInstitutePerYearResult> results = guaranteeService.findInstituteNamesByMaxTotalAmountPerYear();

        // then
        assertThat(results).hasSize(2)
                .contains(new MaxTotalAmountInstitutePerYearResult(data1))
                .contains(new MaxTotalAmountInstitutePerYearResult(data2));
    }

    @Test
    void 특정_연도의_데이터_출력_테스트() {
        // given
        int year = 2020;
        BigInteger maxAmount = BigInteger.valueOf(12345);
        String instituteName = InstituteType.JOOTAEK.name();

        Object[] objects = {year, maxAmount, instituteName};

        given(guaranteeRepository.countByYear(Year.of(year))).willReturn(1);
        given(guaranteeRepository.findInstituteByYearAndMaxTotalAmount(year))
                .willReturn(new ArrayList<>(Collections.singleton(objects)));

        // when
        MaxTotalAmountInstitutePerYearResult result = guaranteeService.findInstituteNameByMaxTotalAmountPerYear(year);

        // then
        assertThat(result)
                .hasFieldOrPropertyWithValue("year", year)
                .hasFieldOrPropertyWithValue("maxAmount", maxAmount)
                .hasFieldOrPropertyWithValue("instituteName", InstituteType.JOOTAEK.getName());
    }

    @Test
    void DB에_해당_연도의_데이터가_없는_경우_예외처리_테스트() {
        // given
        int noDataYear = 2020;
        given(guaranteeRepository.countByYear(Year.of(noDataYear))).willReturn(0);

        // then
        assertThrows(NoSuchDataException.class, () -> guaranteeService.findInstituteNameByMaxTotalAmountPerYear(noDataYear));
    }

}