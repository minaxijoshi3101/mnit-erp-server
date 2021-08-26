package com.mnit.erp.religion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.religion.repository.ReligionRepository;
import com.mnit.erp.religion.model.Religion;
import com.mnit.erp.exceptions.ServiceException;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Prahalad
 */
@Service
public class ReligionService {
    @Autowired
    ReligionRepository religionRepository;
    
    public List<Religion> getAll() {
        List<Religion> dataList = new ArrayList<>();
        religionRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public Religion getById(long id) {
        return religionRepository.findById(id).orElse(null);
    }
    
    public Religion create(Religion createData) {   
        Religion thisReligion = religionRepository.findByName(createData.getName()).orElse(null);
        if(Objects.nonNull(thisReligion)){
            throw new ServiceException("Duplicate Religion. Can't add!");
        }
        return religionRepository.save(createData);
    }
    
    public Religion update(Religion updateData) {
        Religion thisReligion = religionRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisReligion)){
            throw new ServiceException("Religion not found. Can't update!");
        }
        Religion checkReligion = religionRepository.findByName(updateData.getName()).orElse(null);
        if(Objects.nonNull(checkReligion) && !Objects.equals(updateData.getId(), checkReligion.getId())){
            throw new ServiceException("Duplicate Religion. Can't update!");
        }
        return religionRepository.save(updateData);         
    }
}

