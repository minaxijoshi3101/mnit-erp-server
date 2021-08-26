package com.mnit.erp.academic.preExamAttendance.service;

import com.mnit.erp.academic.preExamAttendance.model.PreExamAttendance;
import com.mnit.erp.academic.preExamAttendance.model.Student;

import java.util.List;
/**
 * Contains PreExamAttendance Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 11 June, 2021
 */
public interface PreExamAttendanceService {
    PreExamAttendance add(PreExamAttendance preExamAttendance);
    PreExamAttendance update(PreExamAttendance preExamAttendance);
    PreExamAttendance find(Long preExamAttendanceId);
    List<PreExamAttendance> findAll();
    List<Student> findEligibleStudents(boolean isFa, long examId, long courseId, long sectionId);
}
