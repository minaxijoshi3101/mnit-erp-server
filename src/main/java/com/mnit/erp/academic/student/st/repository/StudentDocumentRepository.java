package com.mnit.erp.academic.student.st.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.student.st.model.StudentDocument;

@Repository
public interface StudentDocumentRepository extends JpaRepository<StudentDocument, Long> {
	List<StudentDocument> findAllByStudent_Id(Long studentId);
	List<StudentDocument> findAllByPreAdmissionData_Id(Long studentId);
}
