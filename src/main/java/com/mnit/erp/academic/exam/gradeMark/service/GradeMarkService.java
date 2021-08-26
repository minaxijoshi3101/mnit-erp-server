package com.mnit.erp.academic.exam.gradeMark.service;

import com.mnit.erp.academic.exam.gradeMark.model.GradeMark;

import java.util.List;

/**
 * Contains Grade Mark Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 29 June, 2021
 */
public interface GradeMarkService {
    List<GradeMark> saveAll(List<GradeMark> gradeMarkList);
    GradeMark add(GradeMark gradeMark);
    GradeMark update(GradeMark gradeMark);
    GradeMark find(Long gradeId);
    List<GradeMark> findAll();
}
