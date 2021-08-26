package com.mnit.erp.academic.admissionType.service;

import com.mnit.erp.academic.admissionType.model.AdmissionType;
import com.mnit.erp.academic.admissionType.repository.AdmissionTypeRepository;
import com.mnit.erp.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdmissionTypeService {

    @Autowired
    AdmissionTypeRepository admissionTypeRepository;

    public AdmissionType add(AdmissionType admissionType) {
        AdmissionType admissionType1 = this.admissionTypeRepository.findByAbbreviation(admissionType.getAbbreviation());
        if (Objects.nonNull(admissionType1)) {
            throw new ServiceException("AdmissionType : " + admissionType1.getAbbreviation() + " already exists. Can't add new!");
        }
        if (this.validate(admissionType))
            return this.admissionTypeRepository.save(admissionType);
        return null;
    }

    public AdmissionType find(Long id) {
        return this.admissionTypeRepository.findById(id).orElse(null);
    }

    public List<AdmissionType> findAll() {
        return this.admissionTypeRepository.findAll();
    }

    private boolean validate(AdmissionType admissionType) {
        return true;
    }

}
