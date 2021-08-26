package com.mnit.erp.academic.student.studentCategory.repository;

import com.mnit.erp.academic.student.studentCategory.model.StudentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains student category repository
 *
 * @author: Tejpal Singh
 * @date: 10 July, 2021
 */
@Repository
public interface StudentCategoryRepository extends JpaRepository<StudentCategory, Long> {
    StudentCategory findByNameAndStudentType_IdAndStudentSubType_Id(String gradeName, Long studentTypeId, Long studentSubTypeId);
    StudentCategory findByNameAndStudentType_IdAndStudentSubType_IdAndIdNot(String gradeName, Long studentTypeId, Long studentSubTypeId, Long Id);
}
