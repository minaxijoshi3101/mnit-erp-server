package com.mnit.erp.hr.repository;

import com.mnit.erp.hr.model.EmployeeStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prahalad
 */
@Repository
public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus, Long>{
    Optional<EmployeeStatus> findByName(String name);
}
