package com.ehem.kakaopay.model.guarantee.repository;

import com.ehem.kakaopay.model.guarantee.domain.Guarantee;
import com.ehem.kakaopay.model.guarantee.domain.vo.Year;
import com.ehem.kakaopay.model.guarantee.service.dto.AverageAmountPerYearResult;
import com.ehem.kakaopay.model.guarantee.service.dto.TotalAmountPerYearResult;
import com.ehem.kakaopay.model.institute.domain.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuaranteeRepository extends JpaRepository<Guarantee, Long> {
    int countByYear(final Year year);

    boolean existsByInstitute(final Institute institute);

    /**
     * 년도별 각 금융기관의 지원금액 합계
     **/
    @Query(value = "SELECT new com.ehem.kakaopay.model.guarantee.service.dto.TotalAmountPerYearResult(g.year, g.institute, sum(g.amount)) " +
            "FROM Guarantee g " +
            "GROUP BY g.institute, g.year")
    List<TotalAmountPerYearResult> findTotalAmountGroupByInstituteNameAndYear();

    /**
     * 각 년도별 가장 많은 금액을 지원한 기관명
     **/
    @Query(value = "SELECT t.year year, g.max max, t.institute_name institute_name " +
            "FROM (SELECT institute_name, year, sum(amount) sum FROM guarantee GROUP BY institute_name, year) t " +
            "JOIN (SELECT year, max(sum) max " +
            "FROM (SELECT institute_name, year, sum(amount) sum FROM guarantee GROUP BY institute_name, year) s GROUP BY year) g " +
            "ON t.sum = g.max", nativeQuery = true)
    List<Object[]> findAllInstituteNamesByYearAndMaxTotalAmount();

    /**
     * 특정 연도에 가장 많은 금액을 지원한 기관명
     **/
    @Query(value = "SELECT t.year year, g.max max, t.institute_name institute_name " +
            "FROM (SELECT institute_name, year, sum(amount) sum FROM guarantee GROUP BY institute_name, year) t " +
            "JOIN (SELECT year, max(sum) max " +
            "FROM (SELECT institute_name, year, sum(amount) sum FROM guarantee GROUP BY institute_name, year) s GROUP BY year) g " +
            "ON t.sum = g.max " +
            "WHERE t.year = ?", nativeQuery = true)
    List<Object[]> findInstituteNameByYearAndMaxTotalAmount(final int year);

    /**
     * 연도별 금융기관 지원금액 평균을 출력
     **/
    @Query(value = "SELECT new com.ehem.kakaopay.model.guarantee.service.dto.AverageAmountPerYearResult(avg(g.amount), g.year, g.institute) " +
            "FROM Guarantee g " +
            "GROUP BY g.institute, g.year")
    List<AverageAmountPerYearResult> findAverageAmountsPerYear();

}
