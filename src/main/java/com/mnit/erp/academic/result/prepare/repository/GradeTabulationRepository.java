package com.mnit.erp.academic.result.prepare.repository;

import com.mnit.erp.academic.result.prepare.model.GradeTabulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains Grade tabulation repository
 *
 * @author: Tejpal Singh
 * @date: 06 July, 2021
 */
@Repository
public interface GradeTabulationRepository extends JpaRepository<GradeTabulation, Long> {

}
