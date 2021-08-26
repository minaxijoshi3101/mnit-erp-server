package com.mnit.erp.hr.repository;

import com.mnit.erp.hr.model.PayAccountType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prahalad
 */
@Repository
public interface PayAccountTypeRepository extends JpaRepository<PayAccountType, Long>{
    Optional<PayAccountType> findByName(String name);
}
