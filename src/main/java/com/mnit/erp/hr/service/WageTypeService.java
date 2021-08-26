package com.mnit.erp.hr.service;

import com.mnit.erp.hr.repository.WageTypeRepository;
import com.mnit.erp.hr.model.WageType;
import com.mnit.erp.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prahalad
 */
@Service
public class WageTypeService {
    @Autowired
    WageTypeRepository wageTypeRepository;
    
    public List<WageType> getAll() {
        List<WageType> dataList = new ArrayList<>();
        wageTypeRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public WageType getById(long id) {
        return wageTypeRepository.findById(id).orElse(null);
    }
    
    public WageType create(WageType createData) {   
        WageType thisWageType = wageTypeRepository.findByName(createData.getName()).orElse(null);
        if(Objects.nonNull(thisWageType)){
            throw new ServiceException("Duplicate Wage Type. Can't add!");
        }
        return wageTypeRepository.save(createData);
    }
    
    public WageType update(WageType updateData) {
        WageType thisWageType = wageTypeRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisWageType)){
            throw new ServiceException("Wage Type not found. Can't update!");
        }
        WageType checkWageType = wageTypeRepository.findByName(updateData.getName()).orElse(null);
        if(Objects.nonNull(checkWageType) && !Objects.equals(updateData.getId(), checkWageType.getId())){
            throw new ServiceException("Duplicate Wage Type. Can't update!");
        }
        return wageTypeRepository.save(updateData);         
    }
}
