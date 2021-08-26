package com.mnit.erp.academic.student.studentType.service;

import com.mnit.erp.academic.student.studentType.model.StudentType;

import java.util.List;

/**
 * Contains student type Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 09 July, 2021
 */
public interface StudentTypeService {
    StudentType add(StudentType studentType);
    StudentType update(StudentType studentType);
    StudentType find(Long gradeId);
    List<StudentType> findAll();
}
