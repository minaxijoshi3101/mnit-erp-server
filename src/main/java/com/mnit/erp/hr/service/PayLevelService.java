package com.mnit.erp.hr.service;

import com.mnit.erp.hr.repository.PayLevelRepository;
import com.mnit.erp.hr.model.PayLevel;
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
public class PayLevelService {
    @Autowired
    PayLevelRepository payLevelRepository;
    
    public List<PayLevel> getAll() {
        List<PayLevel> dataList = new ArrayList<>();
        payLevelRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public PayLevel getById(long id) {
        return payLevelRepository.findById(id).orElse(null);
    }
    
    public PayLevel create(PayLevel createData) {   
        PayLevel thisPayLevel = payLevelRepository.findByName(createData.getName()).orElse(null);
        if(Objects.nonNull(thisPayLevel)){
            throw new ServiceException("Duplicate Pay Level. Can't add!");
        }
        return payLevelRepository.save(createData);
    }
    
    public PayLevel update(PayLevel updateData) {
        PayLevel thisPayLevel = payLevelRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisPayLevel)){
            throw new ServiceException("Pay Level not found. Can't update!");
        }
        PayLevel checkPayLevel = payLevelRepository.findByName(updateData.getName()).orElse(null);
        if(Objects.nonNull(checkPayLevel) && !Objects.equals(updateData.getId(), checkPayLevel.getId())){
            throw new ServiceException("Duplicate Pay Level. Can't update!");
        }
        return payLevelRepository.save(updateData);         
    }
}
