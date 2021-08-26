package com.mnit.erp.academic.student.studentType.repository;

import com.mnit.erp.academic.student.studentType.model.StudentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains student type repository
 *
 * @author: Tejpal Singh
 * @date: 09 July, 2021
 */
@Repository
public interface StudentTypeRepository extends JpaRepository<StudentType, Long> {
    StudentType findByName(String gradeName);
    StudentType findByNameAndIdNot(String gradeName, Long Id);
}
