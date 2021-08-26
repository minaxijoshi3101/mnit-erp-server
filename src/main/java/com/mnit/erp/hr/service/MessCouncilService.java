package com.mnit.erp.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.hr.repository.MessCouncilRepository;
import com.mnit.erp.hr.model.MessCouncil;
import com.mnit.erp.exceptions.ServiceException;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Prahalad
 */
@Service
public class MessCouncilService {
    @Autowired
    MessCouncilRepository messCouncilRepository;
    
    public List<MessCouncil> getAll() {
        List<MessCouncil> dataList = new ArrayList<>();
        messCouncilRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public MessCouncil getById(long id) {
        return messCouncilRepository.findById(id).orElse(null);
    }
    
    public MessCouncil create(MessCouncil createData) {   
        MessCouncil thisMessCouncil = messCouncilRepository.findByName(createData.getName()).orElse(null);
        if(Objects.nonNull(thisMessCouncil)){
            throw new ServiceException("Duplicate Mess Council. Can't add!");
        }
        return messCouncilRepository.save(createData);
    }
    
    public MessCouncil update(MessCouncil updateData) {
        MessCouncil thisMessCouncil = messCouncilRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisMessCouncil)){
            throw new ServiceException("Mess Council not found. Can't update!");
        }
        MessCouncil checkMessCouncil = messCouncilRepository.findByName(updateData.getName()).orElse(null);
        if(Objects.nonNull(checkMessCouncil) && !Objects.equals(updateData.getId(), checkMessCouncil.getId())){
            throw new ServiceException("Duplicate Mess Council. Can't update!");
        }
        return messCouncilRepository.save(updateData);         
    }
}
