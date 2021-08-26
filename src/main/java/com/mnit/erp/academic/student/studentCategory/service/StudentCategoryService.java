package com.mnit.erp.academic.student.studentCategory.service;

import com.mnit.erp.academic.student.studentCategory.model.StudentCategory;

import java.util.List;

/**
 * Contains student category Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 10 July, 2021
 */
public interface StudentCategoryService {
    StudentCategory add(StudentCategory studentCategory);
    StudentCategory update(StudentCategory studentCategory);
    StudentCategory find(Long gradeId);
    List<StudentCategory> findAll();
}
