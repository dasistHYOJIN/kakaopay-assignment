package com.ehem.kakaopay.model.guarantee.repository;

import com.ehem.kakaopay.model.guarantee.domain.Guarantee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuaranteeRepository extends JpaRepository<Guarantee, Long> {

    @Query(value = "SELECT t.year year, g.max max, t.institute_name institute_name " +
            "FROM (SELECT institute_name, year, sum(amount) sum FROM guarantee GROUP BY institute_name, year) t " +
            "JOIN (SELECT year, max(sum) max " +
            "FROM (SELECT institute_name, year, sum(amount) sum FROM guarantee GROUP BY institute_name, year) s GROUP BY year) g " +
            "ON t.sum = g.max", nativeQuery = true)
    List<Object[]> findAllInstitutesByYearAndMaxTotalAmount();

    @Query(value = "SELECT t.year year, g.max max, t.institute_name institute_name " +
            "FROM (SELECT institute_name, year, sum(amount) sum FROM guarantee GROUP BY institute_name, year) t " +
            "JOIN (SELECT year, max(sum) max " +
            "FROM (SELECT institute_name, year, sum(amount) sum FROM guarantee GROUP BY institute_name, year) s GROUP BY year) g " +
            "ON t.sum = g.max " +
            "WHERE t.year = ?", nativeQuery = true)
    Object[] findInstituteByYearAndMaxTotalAmount(final int year);
}
