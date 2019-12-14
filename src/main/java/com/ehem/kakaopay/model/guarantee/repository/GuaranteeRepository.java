package com.ehem.kakaopay.model.guarantee.repository;

import com.ehem.kakaopay.model.guarantee.domain.Guarantee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuaranteeRepository extends JpaRepository<Guarantee, Long> {
}
