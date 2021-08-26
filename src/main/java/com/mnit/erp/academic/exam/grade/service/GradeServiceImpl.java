package com.mnit.erp.academic.exam.grade.service;

import com.mnit.erp.academic.exam.grade.model.Grade;
import com.mnit.erp.academic.exam.grade.repository.GradeRepository;
import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains Grade Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 29 June, 2021
 */
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    GradeRepository gradeRepository;

    @Override
    public Grade add(Grade grade) {
        Grade byName = gradeRepository.findByName(grade.getName());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Grade:" + grade.getName() + " already exists. Can't add again!");
        }

        if(this.validate(grade))
            return this.gradeRepository.save(grade);
        return null;
    }

    @Override
    public Grade update(Grade grade) {
        Grade savedGrade = this.gradeRepository.findById(grade.getId()).orElse(null);
        if(Objects.isNull(savedGrade)){
            throw new ServiceException("Grade not found. Can't update!");
        }
        if(this.validate(grade)) {
            CommonUtils.copyNonNullProperties(grade, savedGrade);
            return this.gradeRepository.save(grade);
        }
        return null;
    }

    @Override
    public Grade find(Long id) {
        return this.gradeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Grade> findAll() {
        return this.gradeRepository.findAll();
    }

    private boolean validate(Grade grade){
        return true;
    }

}
