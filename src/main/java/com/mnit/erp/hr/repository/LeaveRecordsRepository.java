package com.mnit.erp.hr.repository;

import com.mnit.erp.hr.model.LeaveRecords;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prahalad
 */
@Repository
public interface LeaveRecordsRepository extends JpaRepository<LeaveRecords, Long>{
    
}
