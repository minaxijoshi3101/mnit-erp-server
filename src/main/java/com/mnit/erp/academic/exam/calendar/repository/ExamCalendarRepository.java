package com.mnit.erp.academic.exam.calendar.repository;

import com.mnit.erp.academic.exam.calendar.model.ExamCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains Exam calendar repository
 *
 * @author: Tejpal Singh
 * @date: 22 June, 2021
 */
@Repository
public interface ExamCalendarRepository extends JpaRepository<ExamCalendar, Long> {

}
