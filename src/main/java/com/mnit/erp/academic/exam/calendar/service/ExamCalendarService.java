package com.mnit.erp.academic.exam.calendar.service;

import com.mnit.erp.academic.exam.calendar.model.ExamCalendar;

import java.util.List;

/**
 * Contains Exam calendar Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 22 June, 2021
 */
public interface ExamCalendarService {
    ExamCalendar add(ExamCalendar examCalendar);
    ExamCalendar update(ExamCalendar examCalendar);
    ExamCalendar find(Long examCalendarId);
    List<ExamCalendar> findAll();
}
