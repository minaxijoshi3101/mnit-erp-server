package com.mnit.erp.academic.exam.grade.service;

import com.mnit.erp.academic.exam.grade.model.Grade;

import java.util.List;

/**
 * Contains grade Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 29 June, 2021
 */
public interface GradeService {
    Grade add(Grade grade);
    Grade update(Grade grade);
    Grade find(Long gradeId);
    List<Grade> findAll();
}
