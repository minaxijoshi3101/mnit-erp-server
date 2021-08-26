package com.mnit.erp.academic.exam.gradeMark.repository;

import com.mnit.erp.academic.exam.gradeMark.model.GradeMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contains Grade Mark repository
 *
 * @author: Tejpal Singh
 * @date: 29 June, 2021
 */
@Repository
public interface GradeMarkRepository extends JpaRepository<GradeMark, Long> {
    List<GradeMark> findByExam_Id(Long examId);
}
