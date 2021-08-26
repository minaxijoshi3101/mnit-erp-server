package com.mnit.erp.academic.student.st.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.student.st.model.StudentDocument;
import com.mnit.erp.academic.student.st.model.StudentDocumentStatus;

@Repository
public interface StudentDocumentStatusRepository extends JpaRepository<StudentDocumentStatus, Long> {
	StudentDocumentStatus findTopByStudentDocumentOrderByIdDesc(StudentDocument studentDocument);
}
