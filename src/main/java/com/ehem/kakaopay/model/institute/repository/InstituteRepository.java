package com.ehem.kakaopay.model.institute.repository;

import com.ehem.kakaopay.model.institute.domain.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituteRepository extends JpaRepository<Institute, Long> {
}
