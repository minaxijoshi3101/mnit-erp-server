package com.mnit.erp.scholarship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.student.st.model.Student;
import com.mnit.erp.scholarship.model.ApplyType;
import com.mnit.erp.scholarship.model.SanctionType;
import com.mnit.erp.scholarship.model.ScholarshipMaster;
import com.mnit.erp.scholarship.model.ScholarshipStudentDetails;

@Repository
public interface ScholarshipStudentDetailsRepository extends JpaRepository<ScholarshipStudentDetails, Long> {

	List<ScholarshipStudentDetails> findByApplyType(ApplyType applyType);

	List<ScholarshipStudentDetails> findByScholarshipMaster(ScholarshipMaster orElse);

	List<ScholarshipStudentDetails> findByStudent(Student orElse);

	List<ScholarshipStudentDetails> findByScholarshipYear(Long year);

	List<ScholarshipStudentDetails> findBySanctionYear(Long year);

	List<ScholarshipStudentDetails> findBySanctionType(SanctionType sanctionType);

	List<ScholarshipStudentDetails> findByFinancialYear(Long year);
}
