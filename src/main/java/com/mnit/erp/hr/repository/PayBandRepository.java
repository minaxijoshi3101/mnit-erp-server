package com.mnit.erp.hr.repository;

import com.mnit.erp.hr.model.PayBand;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prahalad
 */
@Repository
public interface PayBandRepository extends JpaRepository<PayBand, Long> {
    Optional<PayBand> findByName(String name);    
}
