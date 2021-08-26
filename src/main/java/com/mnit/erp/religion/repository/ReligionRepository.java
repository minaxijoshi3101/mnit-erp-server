package com.mnit.erp.religion.repository;

import com.mnit.erp.religion.model.Religion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prahalad
 */
@Repository
public interface ReligionRepository extends JpaRepository<Religion, Long>{
    Optional<Religion> findByName(String name);
}
