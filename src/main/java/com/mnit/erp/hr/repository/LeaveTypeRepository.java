package com.mnit.erp.hr.repository;

import com.mnit.erp.hr.model.LeaveType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prahalad
 */
@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long>{
    Optional<LeaveType> findByName(String name);
    
    Optional<LeaveType> findByAbbreviation(String abbreviation);
}
