package com.mnit.erp.academic.student.studentTypeChangeRequest.service;

import com.mnit.erp.academic.student.studentTypeChangeRequest.model.StudentTypeChangeRequest;

import java.util.List;

/**
 * Contains student type change request Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 22 July, 2021
 */
public interface StudentTypeChangeRequestService {
    StudentTypeChangeRequest add(StudentTypeChangeRequest studentTypeChangeRequest);
    StudentTypeChangeRequest update(StudentTypeChangeRequest studentTypeChangeRequest);
    StudentTypeChangeRequest find(Long studentTypeChangeRequestId);
    List<StudentTypeChangeRequest> findAll();
    StudentTypeChangeRequest approveOrReject(StudentTypeChangeRequest studentTypeChangeRequest);
}
