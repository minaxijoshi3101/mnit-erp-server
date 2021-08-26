package com.mnit.erp.hr.repository;

import org.springframework.stereotype.Repository;

import com.mnit.erp.hr.model.GradePay;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Prahalad
 */
@Repository
public interface GradePayRepository extends JpaRepository<GradePay, Long> {
     Optional<GradePay> findByName(String name); 
}
