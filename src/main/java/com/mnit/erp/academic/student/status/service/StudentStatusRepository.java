package com.mnit.erp.academic.student.status.service;

import com.mnit.erp.academic.student.status.model.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentStatusRepository extends JpaRepository<StudentStatus, Long> {
    StudentStatus findByName(String name);
}
