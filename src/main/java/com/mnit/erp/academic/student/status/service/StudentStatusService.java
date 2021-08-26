package com.mnit.erp.academic.student.status.service;

import com.mnit.erp.academic.student.status.model.StudentStatus;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentStatusService {

    @Autowired
    StudentStatusRepository studentStatusRepository;

    public StudentStatus add(StudentStatus studentStatus) {
        StudentStatus studentStatus1 = this.studentStatusRepository.findByName(studentStatus.getName());
        if(Objects.nonNull(studentStatus1)){
            throw new ServiceException("Student status : " + studentStatus.getName() + " already exists. Can't add new!");
        }
        if(this.validate(studentStatus)){
            return this.studentStatusRepository.save(studentStatus);
        }
        return null;
    }

    public StudentStatus update(StudentStatus studentStatus) {
        StudentStatus studentStatus1 = this.studentStatusRepository.findById(studentStatus.getId()).orElse(null);
        if(Objects.isNull(studentStatus1)){
            throw new ServiceException("Status not found. Can't update!");
        }
        if(this.validate(studentStatus)){
            CommonUtils.copyNonNullProperties(studentStatus, studentStatus1);
            return this.studentStatusRepository.save(studentStatus);
        }
        return null;
    }

    public StudentStatus find(Long id){
        return this.studentStatusRepository.findById(id).orElse(null);
    }

    public List<StudentStatus> findAll(){
        return this.studentStatusRepository.findAll();
    }

    private boolean validate(StudentStatus studentStatus){
        return  true;
    }

}
