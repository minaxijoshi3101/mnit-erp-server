package com.mnit.erp.academic.student.studentTypeChangeRequest.repository;

import com.mnit.erp.academic.student.studentTypeChangeRequest.model.RequestApproveStatus;
import com.mnit.erp.academic.student.studentTypeChangeRequest.model.StudentTypeChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains student type change request repository
 *
 * @author: Tejpal Singh
 * @date: 22 July, 2021
 */
@Repository
public interface StudentTypeChangeRequestRepository extends JpaRepository<StudentTypeChangeRequest, Long> {
    StudentTypeChangeRequest findByStudent_IdAndStudentType_IdAndStudentSubType_IdAndStudentCategory_IdAndStatus(Long studentId, Long studentTypeId, Long studentSubTypeId, Long studentCategoryId, RequestApproveStatus status);
    StudentTypeChangeRequest findByIdAndStatus(Long Id, RequestApproveStatus status);
    StudentTypeChangeRequest findByStudent_IdAndStudentType_IdAndStudentSubType_IdAndStudentCategory_IdAndStatusAndIdNot(Long studentId, Long studentTypeId, Long studentSubTypeId, Long studentCategoryId, RequestApproveStatus status, Long Id);
}
