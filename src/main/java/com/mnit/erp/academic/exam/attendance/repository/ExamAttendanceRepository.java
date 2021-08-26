package com.mnit.erp.academic.exam.attendance.repository;

import com.mnit.erp.academic.exam.attendance.model.ExamAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains Exam attendance repository
 *
 * @author: Tejpal Singh
 * @date: 24 June, 2021
 */
@Repository
public interface ExamAttendanceRepository extends JpaRepository<ExamAttendance, Long> {

}
