package com.mnit.erp.academic.student.studentSubType.service;

import com.mnit.erp.academic.student.studentSubType.model.StudentSubType;
import com.mnit.erp.academic.student.studentSubType.repository.StudentSubTypeRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains student sub type Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 10 July, 2021
 */
@Service
public class StudentSubTypeServiceImpl implements StudentSubTypeService {

    @Autowired
    StudentSubTypeRepository studentSubTypeRepository;

    @Override
    public StudentSubType add(StudentSubType studentSubType) {
        StudentSubType byName = studentSubTypeRepository.findByNameAndStudentType_Id(studentSubType.getName(), studentSubType.getStudentTypeId());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Student sub type:" + studentSubType.getName() + " already exists. Can't add again!");
        }

        if(this.validate(studentSubType))
            return this.studentSubTypeRepository.save(studentSubType);
        return null;
    }

    @Override
    public StudentSubType update(StudentSubType studentSubType) {
        StudentSubType byNameAndIdNot = studentSubTypeRepository.findByNameAndStudentType_IdAndIdNot(studentSubType.getName(), studentSubType.getStudentTypeId(), studentSubType.getId());
        if(Objects.nonNull(byNameAndIdNot)){
            throw new ServiceException("Student sub type:" + studentSubType.getName() + " already exists. Can't update!");
        }

        StudentSubType savedStudentSubType = this.studentSubTypeRepository.findById(studentSubType.getId()).orElse(null);
        if(Objects.isNull(savedStudentSubType)){
            throw new ServiceException("Student sub type not found. Can't update!");
        }
        if(this.validate(studentSubType)) {
            CommonUtils.copyNonNullProperties(studentSubType, savedStudentSubType);
            return this.studentSubTypeRepository.save(studentSubType);
        }
        return null;
    }

    @Override
    public StudentSubType find(Long id) {
        return this.studentSubTypeRepository.findById(id).orElse(null);
    }

    @Override
    public List<StudentSubType> findAll() {
        return this.studentSubTypeRepository.findAll();
    }

    private boolean validate(StudentSubType studentSubType){
        return true;
    }

}
