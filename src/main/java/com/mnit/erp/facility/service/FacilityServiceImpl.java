package com.mnit.erp.facility.service;

import com.mnit.erp.facility.model.Facility;
import com.mnit.erp.facility.repository.FacilityRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains Facility Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 16 June, 2021
 */
@Service
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    FacilityRepository facilityRepository;

    @Override
    public Facility add(Facility facility) {
        Facility byName = facilityRepository.findByName(facility.getName());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Facility:" + facility.getName() + " already exists. Can't add again!");
        }
        if(this.validate(facility))
            return this.facilityRepository.save(facility);
        return null;
    }

    @Override
    public Facility update(Facility facility) {
        Facility savedFacility = this.facilityRepository.findById(facility.getId()).orElse(null);
        if(Objects.isNull(savedFacility)){
            throw new ServiceException("Facility not found. Can't update!");
        }
        if(this.validate(facility)) {
            CommonUtils.copyNonNullProperties(facility, savedFacility);
            return this.facilityRepository.save(facility);
        }
        return null;
    }

    @Override
    public Facility find(Long id) {
        return this.facilityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Facility> findAll() {
        return this.facilityRepository.findAll();
    }

    private boolean validate(Facility facility){
        return true;
    }

}
