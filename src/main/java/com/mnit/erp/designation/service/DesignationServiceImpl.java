package com.mnit.erp.designation.service;

import com.mnit.erp.designation.model.Designation;
import com.mnit.erp.designation.repository.DesignationRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DesignationServiceImpl implements DesignationService{

    @Autowired
    DesignationRepository designationRepository;

    @Override
    public Designation add(Designation designation) {
        if(this.validate(designation))
            return this.designationRepository.save(designation);
        return null;
    }

    @Override
    public Designation update(Designation designation) {
        Optional<Designation> designation1 = this.designationRepository.findById(designation.getId());
        if(Objects.isNull(designation1)){
            throw new ServiceException("Designation not found. Can't update!");
        }
        if(this.validate(designation)){
            CommonUtils.copyNonNullProperties(designation, designation1);
            return this.designationRepository.save(designation);
        }
        return null;
    }

    @Override
    public Designation find(Long id) {
        return this.designationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Designation> findAll() {
        return this.designationRepository.findAll();
    }

    private boolean validate(Designation designation){
        return true;
    }
}
