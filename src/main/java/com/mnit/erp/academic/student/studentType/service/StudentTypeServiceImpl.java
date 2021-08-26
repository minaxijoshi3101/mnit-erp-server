package com.mnit.erp.academic.student.studentType.service;

import com.mnit.erp.academic.student.studentType.model.StudentType;
import com.mnit.erp.academic.student.studentType.repository.StudentTypeRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains student type Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 09 July, 2021
 */
@Service
public class StudentTypeServiceImpl implements StudentTypeService {

    @Autowired
    StudentTypeRepository studentTypeRepository;

    @Override
    public StudentType add(StudentType studentType) {
        StudentType byName = studentTypeRepository.findByName(studentType.getName());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Student type:" + studentType.getName() + " already exists. Can't add again!");
        }

        if(this.validate(studentType))
            return this.studentTypeRepository.save(studentType);
        return null;
    }

    @Override
    public StudentType update(StudentType studentType) {
        StudentType byNameAndIdNot = studentTypeRepository.findByNameAndIdNot(studentType.getName(), studentType.getId());
        if(Objects.nonNull(byNameAndIdNot)){
            throw new ServiceException("Student type:" + studentType.getName() + " already exists. Can't update!");
        }

        StudentType savedStudentType = this.studentTypeRepository.findById(studentType.getId()).orElse(null);
        if(Objects.isNull(savedStudentType)){
            throw new ServiceException("Student type not found. Can't update!");
        }
        if(this.validate(studentType)) {
            CommonUtils.copyNonNullProperties(studentType, savedStudentType);
            return this.studentTypeRepository.save(studentType);
        }
        return null;
    }

    @Override
    public StudentType find(Long id) {
        return this.studentTypeRepository.findById(id).orElse(null);
    }

    @Override
    public List<StudentType> findAll() {
        return this.studentTypeRepository.findAll();
    }

    private boolean validate(StudentType studentType){
        return true;
    }

}
