package com.mnit.erp.academic.specialization.service;

import java.util.List;
import java.util.Objects;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.academic.branch.repository.BranchRepository;
import com.mnit.erp.academic.specialization.model.Specialization;
import com.mnit.erp.academic.specialization.repository.SpecializationRepository;

@Service
public class SpecializationSerivce {

    @Autowired
    SpecializationRepository specializationRepository;

    @Autowired
    BranchRepository branchRepository;

    public Specialization add(Specialization specialization) {
        if(this.validate(specialization)){
            return this.specializationRepository.save(specialization);
        }
        return null;
    }


    public Specialization update(Specialization specialization) {
        Specialization savedSpecialization = this.specializationRepository.findById(specialization.getId()).orElse(null);
        if(Objects.isNull(savedSpecialization)){
            throw new ServiceException("Specialization not found. Can't update!");
        }
        if(this.validate(specialization)){
            CommonUtils.copyNonNullProperties(specialization,savedSpecialization);
            return this.specializationRepository.save(specialization);
        }
        return null;
    }
    private boolean validate(Specialization specialization){
        return true;
    }

    public Specialization find(Long id) {
        return this.specializationRepository.findById(id).orElse(null);
    }

    public List<Specialization> findAll() {
        return this.specializationRepository.findAll();
    }

    public List<Specialization> findAllUnderBranch(Long branchId) {
        return this.specializationRepository.findByBranch(branchRepository.findById(branchId).orElse(null));
    }
}
