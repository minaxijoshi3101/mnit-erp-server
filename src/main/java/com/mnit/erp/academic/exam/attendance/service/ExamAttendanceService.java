package com.mnit.erp.academic.exam.attendance.service;

import com.mnit.erp.academic.exam.attendance.model.ExamAttendance;

import java.util.List;

/**
 * Contains Exam attendance Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 24 June, 2021
 */
public interface ExamAttendanceService {
    ExamAttendance add(ExamAttendance examAttendance);
    ExamAttendance update(ExamAttendance examAttendance);
    ExamAttendance find(Long examAttendanceId);
    List<ExamAttendance> findAll();
}
