package com.mnit.erp.hr.repository;

import com.mnit.erp.hr.model.MessCouncil;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prahalad
 */
@Repository
public interface MessCouncilRepository extends JpaRepository<MessCouncil, Long>{
    Optional<MessCouncil> findByName(String name);
}
