package com.ehem.kakaopay.model.guarantee.service;

import com.ehem.kakaopay.model.guarantee.domain.vo.Year;
import com.ehem.kakaopay.model.guarantee.exception.NoSuchDataException;
import com.ehem.kakaopay.model.guarantee.repository.GuaranteeRepository;
import com.ehem.kakaopay.model.guarantee.service.dto.*;
import com.ehem.kakaopay.model.institute.domain.Institute;
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

        given(guaranteeRepository.findAllInstituteNamesByYearAndMaxTotalAmount()).willReturn(data);

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
        given(guaranteeRepository.findInstituteNameByYearAndMaxTotalAmount(year))
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

    @Test
    void 연도별_각_금융기관의_지원금액_합계_출력_테스트() {
        // given
        int year1 = 2020;
        long sumAmount1 = 12345L;
        String instituteName1 = InstituteType.JOOTAEK.name();

        int year2 = 2021;
        long sumAmount2 = 145L;
        String instituteName2 = InstituteType.WOORI.name();

        given(guaranteeRepository.findTotalAmountGroupByInstituteNameAndYear())
                .willReturn(Arrays.asList(
                        new TotalAmountPerYearResult(Year.of(year1), new Institute(InstituteType.ofName(instituteName1)), sumAmount1),
                        new TotalAmountPerYearResult(Year.of(year2), new Institute(InstituteType.ofName(instituteName2)), sumAmount2)
                ));

        // when
        List<TotalAmountAndInstitutePerYearResponseDto> results = guaranteeService.findTotalAmountPerYear();

        // then
        assertThat(results).hasSize(2);
        assertThat(results.get(0))
                .hasFieldOrPropertyWithValue("year", year1 + "년")
                .hasFieldOrPropertyWithValue("totalAmount", sumAmount1);
    }

    @Test
    void 존재하지_않는_금융기관의_연도별_평균_최대최소값을_요청하는_경우_예외처리_테스트() {
        // given
        InstituteType instituteType = InstituteType.OEHWAN;

        given(guaranteeRepository.existsByInstitute(new Institute(instituteType))).willReturn(false);

        // then
        assertThrows(NoSuchDataException.class, () -> guaranteeService.findMinMaxAverageAmounts(instituteType.getName()));
    }

    @Test
    void 특정_금융기관의_연도별_평균_최대최소값_출력_테스트() {
        // given
        double avg1 = 0;
        Year year1 = Year.of(2010);
        Institute institute1 = new Institute(InstituteType.OEHWAN);

        double avg2 = 123;
        Year year2 = Year.of(2011);
        Institute institute2 = new Institute(InstituteType.OEHWAN);

        double avg3 = 13;
        Year year3 = Year.of(2012);
        Institute institute3 = new Institute(InstituteType.OEHWAN);

        double avg4 = 120;
        Year year4 = Year.of(2013);
        Institute institute4 = new Institute(InstituteType.KOOKMIN);

        double avg5 = 25;
        Year year5 = Year.of(2010);
        Institute institute5 = new Institute(InstituteType.KOOKMIN);

        double avg6 = 23;
        Year year6 = Year.of(2011);
        Institute institute6 = new Institute(InstituteType.KOOKMIN);

        double avg7 = 18;
        Year year7 = Year.of(2012);
        Institute institute7 = new Institute(InstituteType.SHINHAN);

        double avg8 = 32;
        Year year8 = Year.of(2013);
        Institute institute8 = new Institute(InstituteType.SHINHAN);

        given(guaranteeRepository.existsByInstitute(institute1)).willReturn(true);
        given(guaranteeRepository.findAverageAmountsPerYear()).willReturn(Arrays.asList(
                new AverageAmountPerYearResult(avg1, year1, institute1),
                new AverageAmountPerYearResult(avg2, year2, institute2),
                new AverageAmountPerYearResult(avg3, year3, institute3),
                new AverageAmountPerYearResult(avg4, year4, institute4),
                new AverageAmountPerYearResult(avg5, year5, institute5),
                new AverageAmountPerYearResult(avg6, year6, institute6),
                new AverageAmountPerYearResult(avg7, year7, institute7),
                new AverageAmountPerYearResult(avg8, year8, institute8)
        ));

        // when
        AverageAmountPerYearResponseDto result = guaranteeService.findMinMaxAverageAmounts(institute1.getName());

        // then
        assertThat(result.getMinAmount())
                .hasFieldOrPropertyWithValue("year", 2010)
                .hasFieldOrPropertyWithValue("amount", 0.0);
        assertThat(result.getMaxAmount())
                .hasFieldOrPropertyWithValue("year", 2011)
                .hasFieldOrPropertyWithValue("amount", 123.0);

    }

}