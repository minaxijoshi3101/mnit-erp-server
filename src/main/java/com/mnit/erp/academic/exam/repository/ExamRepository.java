package com.mnit.erp.academic.exam.repository;

import com.mnit.erp.academic.exam.model.Exam;
import com.mnit.erp.academic.exam.model.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains PreExamAttendance repository
 *
 * @author: Tejpal Singh
 * @date: 08 June, 2021
 */
@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    Exam findByNameAndExamType(String name, ExamType examType);
}
