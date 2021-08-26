package com.mnit.erp.hr.repository;

import com.mnit.erp.hr.model.WageType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prahalad
 */
@Repository
public interface WageTypeRepository extends JpaRepository<WageType, Long>{
    Optional<WageType> findByName(String name);
}
