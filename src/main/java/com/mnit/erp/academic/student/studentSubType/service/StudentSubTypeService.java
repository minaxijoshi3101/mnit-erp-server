package com.mnit.erp.academic.student.studentSubType.service;

import com.mnit.erp.academic.student.studentSubType.model.StudentSubType;

import java.util.List;

/**
 * Contains student sub type Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 10 July, 2021
 */
public interface StudentSubTypeService {
    StudentSubType add(StudentSubType studentSubType);
    StudentSubType update(StudentSubType studentSubType);
    StudentSubType find(Long gradeId);
    List<StudentSubType> findAll();
}
