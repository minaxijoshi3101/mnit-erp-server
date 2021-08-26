package com.mnit.erp.academic.exam.grade.repository;

import com.mnit.erp.academic.exam.grade.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains grade repository
 *
 * @author: Tejpal Singh
 * @date: 29 June, 2021
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade findByName(String gradeName);
}
