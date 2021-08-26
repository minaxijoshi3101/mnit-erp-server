package com.mnit.erp.academic.exam.gradeMark.service;

import com.mnit.erp.academic.exam.gradeMark.model.GradeMark;
import com.mnit.erp.academic.exam.gradeMark.repository.GradeMarkRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains Grade Mark Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 29 June, 2021
 */
@Service
public class GradeMarkServiceImpl implements GradeMarkService {

    @Autowired
    GradeMarkRepository gradeMarkRepository;

    @Override
    public List<GradeMark> saveAll(List<GradeMark> gradeMarks) {

        if(this.validate(gradeMarks))
            return this.gradeMarkRepository.saveAll(gradeMarks);
        return null;
    }

    @Override
    public GradeMark add(GradeMark gradeMark) {

        if(this.validate(gradeMark))
            return this.gradeMarkRepository.save(gradeMark);
        return null;
    }

    @Override
    public GradeMark update(GradeMark gradeMark) {
        GradeMark savedGradeMark = this.gradeMarkRepository.findById(gradeMark.getId()).orElse(null);
        if(Objects.isNull(savedGradeMark)){
            throw new ServiceException("Grade mark not found. Can't update!");
        }
        if(this.validate(gradeMark)) {
            CommonUtils.copyNonNullProperties(gradeMark, savedGradeMark);
            return this.gradeMarkRepository.save(gradeMark);
        }
        return null;
    }

    @Override
    public GradeMark find(Long id) {
        return this.gradeMarkRepository.findById(id).orElse(null);
    }

    @Override
    public List<GradeMark> findAll() {
        return this.gradeMarkRepository.findAll();
    }

    private boolean validate(GradeMark gradeMark){
        return true;
    }
    private boolean validate(List<GradeMark> gradeMarks){
        return true;
    }

}
