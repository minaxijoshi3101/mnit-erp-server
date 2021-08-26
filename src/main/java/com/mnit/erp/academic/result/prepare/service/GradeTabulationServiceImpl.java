package com.mnit.erp.academic.result.prepare.service;

import com.mnit.erp.academic.result.prepare.model.GradeTabulation;
import com.mnit.erp.academic.result.prepare.repository.GradeTabulationRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains Grade Tabulation Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 06 July, 2021
 */
@Service
public class GradeTabulationServiceImpl implements GradeTabulationService {

    @Autowired
    GradeTabulationRepository gradeTabulationRepository;

    @Override
    public List<GradeTabulation> saveAll(List<GradeTabulation> gradeTabulations) {

        if(this.validate(gradeTabulations))
            return this.gradeTabulationRepository.saveAll(gradeTabulations);
        return null;
    }

    @Override
    public GradeTabulation add(GradeTabulation gradeTabulation) {

        if(this.validate(gradeTabulation))
            return this.gradeTabulationRepository.save(gradeTabulation);
        return null;
    }

    @Override
    public GradeTabulation update(GradeTabulation gradeTabulation) {
        GradeTabulation savedGradeTabulation = this.gradeTabulationRepository.findById(gradeTabulation.getId()).orElse(null);
        if(Objects.isNull(savedGradeTabulation)){
            throw new ServiceException("Grade tabulation record not found. Can't update!");
        }
        if(this.validate(gradeTabulation)) {
            CommonUtils.copyNonNullProperties(gradeTabulation, savedGradeTabulation);
            return this.gradeTabulationRepository.save(gradeTabulation);
        }
        return null;
    }

    @Override
    public GradeTabulation find(Long id) {
        return this.gradeTabulationRepository.findById(id).orElse(null);
    }

    @Override
    public List<GradeTabulation> findAll() {
        return this.gradeTabulationRepository.findAll();
    }

    private boolean validate(GradeTabulation gradeTabulation){
        return true;
    }
    private boolean validate(List<GradeTabulation> gradeTabulations){
        return true;
    }

}
