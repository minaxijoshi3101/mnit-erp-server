package com.mnit.erp.hr.repository;

import com.mnit.erp.hr.model.PayLevel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prahalad
 */
@Repository
public interface PayLevelRepository extends JpaRepository<PayLevel, Long>{
    Optional<PayLevel> findByName(String name);
}
