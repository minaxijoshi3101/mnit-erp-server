package com.mnit.erp.academic.exam.service;

import com.mnit.erp.academic.exam.model.Exam;

import java.util.List;
/**
 * Contains PreExamAttendance Service
 *
 * @author: Tejpal Singh
 * @declaration: add/upate/find/findAll
 * @date: 08 June, 2021
 */
public interface ExamService {
    Exam add(Exam exam);
    Exam update(Exam exam);
    Exam find(Long examId);
    List<Exam> findAll();
}
