package com.mnit.erp.hr.repository;

import com.mnit.erp.hr.model.CostCenter;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prahalad
 */
@Repository
public interface CostCenterRepository extends JpaRepository<CostCenter, Long>{
    Optional<CostCenter> findByName(String name);
}
