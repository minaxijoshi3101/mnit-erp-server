package com.mnit.erp.academic.student.studentCategory.service;

import com.mnit.erp.academic.student.studentCategory.model.StudentCategory;
import com.mnit.erp.academic.student.studentCategory.repository.StudentCategoryRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains student category Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 10 July, 2021
 */
@Service
public class StudentCategoryServiceImpl implements StudentCategoryService {

    @Autowired
    StudentCategoryRepository studentCategoryRepository;

    @Override
    public StudentCategory add(StudentCategory studentCategory) {
        StudentCategory byName = studentCategoryRepository.findByNameAndStudentType_IdAndStudentSubType_Id(studentCategory.getName(), studentCategory.getStudentTypeId(), studentCategory.getStudentSubTypeId());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Student category:" + studentCategory.getName() + " already exists. Can't add again!");
        }

        if(this.validate(studentCategory))
            return this.studentCategoryRepository.save(studentCategory);
        return null;
    }

    @Override
    public StudentCategory update(StudentCategory studentCategory) {
        StudentCategory byNameAndIdNot = studentCategoryRepository.findByNameAndStudentType_IdAndStudentSubType_IdAndIdNot(studentCategory.getName(), studentCategory.getStudentTypeId(), studentCategory.getStudentSubTypeId(), studentCategory.getId());
        if(Objects.nonNull(byNameAndIdNot)){
            throw new ServiceException("Student category:" + studentCategory.getName() + " already exists. Can't update!");
        }

        StudentCategory savedStudentCategory = this.studentCategoryRepository.findById(studentCategory.getId()).orElse(null);
        if(Objects.isNull(savedStudentCategory)){
            throw new ServiceException("Student category not found. Can't update!");
        }
        if(this.validate(studentCategory)) {
            CommonUtils.copyNonNullProperties(studentCategory, savedStudentCategory);
            return this.studentCategoryRepository.save(studentCategory);
        }
        return null;
    }

    @Override
    public StudentCategory find(Long id) {
        return this.studentCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<StudentCategory> findAll() {
        return this.studentCategoryRepository.findAll();
    }

    private boolean validate(StudentCategory studentCategory){
        return true;
    }

}
