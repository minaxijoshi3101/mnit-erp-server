package com.mnit.erp.academic.student.studentTypeChangeRequest.service;

import com.mnit.erp.academic.student.st.model.Student;
import com.mnit.erp.academic.student.st.repository.StudentRepository;
import com.mnit.erp.academic.student.studentTypeChangeRequest.model.RequestApproveStatus;
import com.mnit.erp.academic.student.studentTypeChangeRequest.model.StudentTypeChangeRequest;
import com.mnit.erp.academic.student.studentTypeChangeRequest.repository.StudentTypeChangeRequestRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.security.CurrentUser;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * Contains student type change request Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 22 July, 2021
 */
@Service
public class StudentTypeChangeRequestServiceImpl implements StudentTypeChangeRequestService {

    @Autowired
    StudentTypeChangeRequestRepository studentTypeChangeRequestRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentTypeChangeRequest add(StudentTypeChangeRequest studentTypeChangeRequest) {

        StudentTypeChangeRequest byPendingStatus = this.studentTypeChangeRequestRepository.findByStudent_IdAndStudentType_IdAndStudentSubType_IdAndStudentCategory_IdAndStatus(studentTypeChangeRequest.getStudentId(), studentTypeChangeRequest.getStudentTypeId(), studentTypeChangeRequest.getStudentSubTypeId(), studentTypeChangeRequest.getStudentCategoryId(), RequestApproveStatus.PENDING);
        if(Objects.nonNull(byPendingStatus)){
            throw new ServiceException("Student type change request for this student already exists. Can't add again!");
        }
        studentTypeChangeRequest.setStatus(RequestApproveStatus.PENDING);
        if(this.validate(studentTypeChangeRequest))
            return this.studentTypeChangeRequestRepository.save(studentTypeChangeRequest);
        return null;
    }

    @Override
    public StudentTypeChangeRequest update(StudentTypeChangeRequest studentTypeChangeRequest) {
        StudentTypeChangeRequest byPendingStatusAndIdNot = this.studentTypeChangeRequestRepository.findByStudent_IdAndStudentType_IdAndStudentSubType_IdAndStudentCategory_IdAndStatusAndIdNot(studentTypeChangeRequest.getStudentId(), studentTypeChangeRequest.getStudentTypeId(), studentTypeChangeRequest.getStudentSubTypeId(), studentTypeChangeRequest.getStudentCategoryId(), RequestApproveStatus.PENDING, studentTypeChangeRequest.getId());
        if(Objects.nonNull(byPendingStatusAndIdNot)){
            throw new ServiceException("Student type change request for this student already exists. Can't update!");
        }

        StudentTypeChangeRequest savedStudentTypeChangeRequest = this.studentTypeChangeRequestRepository.findById(studentTypeChangeRequest.getId()).orElse(null);
        if(Objects.isNull(savedStudentTypeChangeRequest)){
            throw new ServiceException("Student type change request not found. Can't update!");
        }
        studentTypeChangeRequest.setStatus(RequestApproveStatus.PENDING);
        if(this.validate(studentTypeChangeRequest)) {
            CommonUtils.copyNonNullProperties(studentTypeChangeRequest, savedStudentTypeChangeRequest);
            return this.studentTypeChangeRequestRepository.save(studentTypeChangeRequest);
        }
        return null;
    }

    @Override
    @Transactional
    public StudentTypeChangeRequest approveOrReject(StudentTypeChangeRequest studentTypeChangeRequest) {
        StudentTypeChangeRequest savedStudentTypeChangeRequest = this.studentTypeChangeRequestRepository.findByIdAndStatus(studentTypeChangeRequest.getId(), RequestApproveStatus.PENDING);
        if(Objects.isNull(savedStudentTypeChangeRequest)){
            throw new ServiceException("Student type change request not found. Can't approve!");
        }
        if(this.validate(studentTypeChangeRequest)) {
            if(studentTypeChangeRequest.getStatus() == RequestApproveStatus.APPROVED) {
                Student student = this.studentRepository.findById(savedStudentTypeChangeRequest.getStudentId()).orElse(null);
                student.setStudentTypeId(savedStudentTypeChangeRequest.getStudentTypeId());
                student.setStudentSubTypeId(savedStudentTypeChangeRequest.getStudentSubTypeId());
                student.setStudentCategoryId(savedStudentTypeChangeRequest.getStudentCategoryId());
                this.studentRepository.save(student);
            }
            savedStudentTypeChangeRequest.setStatus(studentTypeChangeRequest.getStatus());
            savedStudentTypeChangeRequest.setApprovedById(CurrentUser.getCurrentInMemoryUser().getId());
            return this.studentTypeChangeRequestRepository.save(savedStudentTypeChangeRequest);
        }
        return null;
    }

    @Override
    public StudentTypeChangeRequest find(Long id) {
        return this.studentTypeChangeRequestRepository.findById(id).orElse(null);
    }

    @Override
    public List<StudentTypeChangeRequest> findAll() {
        return this.studentTypeChangeRequestRepository.findAll();
    }

    private boolean validate(StudentTypeChangeRequest studentTypeChangeRequest){
        return true;
    }

}
