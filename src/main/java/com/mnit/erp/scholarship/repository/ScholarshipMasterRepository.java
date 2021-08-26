package com.mnit.erp.scholarship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.scholarship.model.ScholarshipMaster;
import com.mnit.erp.scholarship.model.ScholarshipType;

@Repository

public interface ScholarshipMasterRepository extends JpaRepository<ScholarshipMaster,Long > {

	List<ScholarshipMaster> findByScholarshipName(String name);
	List<ScholarshipMaster> findByScholarshipType(ScholarshipType orElse);	
	List<ScholarshipMaster> findByProgram(Program orElse);

}
