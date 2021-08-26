package com.mnit.erp.academic.result.prepare.service;

import com.mnit.erp.academic.result.prepare.model.GradeTabulation;

import java.util.List;

/**
 * Contains Grade Tabulation Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 06 July, 2021
 */
public interface GradeTabulationService {
    List<GradeTabulation> saveAll(List<GradeTabulation> gradeTabulationList);
    GradeTabulation add(GradeTabulation gradeTabulation);
    GradeTabulation update(GradeTabulation gradeTabulation);
    GradeTabulation find(Long gradeId);
    List<GradeTabulation> findAll();
}
