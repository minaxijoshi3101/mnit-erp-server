package com.mnit.erp.academic.student.st.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.student.st.model.StudentSemester;

@Repository
public interface StudentSemesterRepository extends JpaRepository<StudentSemester, Long> {
}
