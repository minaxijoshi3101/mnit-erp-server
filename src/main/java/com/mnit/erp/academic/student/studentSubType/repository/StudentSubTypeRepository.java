package com.mnit.erp.academic.student.studentSubType.repository;

import com.mnit.erp.academic.student.studentSubType.model.StudentSubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains student sub type repository
 *
 * @author: Tejpal Singh
 * @date: 10 July, 2021
 */
@Repository
public interface StudentSubTypeRepository extends JpaRepository<StudentSubType, Long> {
    StudentSubType findByNameAndStudentType_Id(String gradeName, Long studentTypeId);
    StudentSubType findByNameAndStudentType_IdAndIdNot(String gradeName, Long studentTypeId, Long Id);
}
