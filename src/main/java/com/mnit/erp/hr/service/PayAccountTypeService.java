package com.mnit.erp.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.hr.model.PayAccountType;
import com.mnit.erp.exceptions.ServiceException;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import com.mnit.erp.hr.repository.PayAccountTypeRepository;

/**
 *
 * @author Prahalad
 */
@Service
public class PayAccountTypeService {
    @Autowired
    PayAccountTypeRepository payAccTypeRepository;
    
    public List<PayAccountType> getAll() {
        List<PayAccountType> dataList = new ArrayList<>();
        payAccTypeRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public PayAccountType getById(long id) {
        return payAccTypeRepository.findById(id).orElse(null);
    }
    
    public PayAccountType create(PayAccountType createData) {   
        PayAccountType thisPayAccType = payAccTypeRepository.findByName(createData.getName()).orElse(null);
        if(Objects.nonNull(thisPayAccType)){
            throw new ServiceException("Duplicate PayAccType. Can't add!");
        }
        return payAccTypeRepository.save(createData);
    }
    
    public PayAccountType update(PayAccountType updateData) {
        PayAccountType thisPayAccType = payAccTypeRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisPayAccType)){
            throw new ServiceException("PayAccType not found. Can't update!");
        }
        PayAccountType checkPayAccType = payAccTypeRepository.findByName(updateData.getName()).orElse(null);
        if(Objects.nonNull(checkPayAccType) && !Objects.equals(updateData.getId(), checkPayAccType.getId())){
            throw new ServiceException("Duplicate PayAccType. Can't update!");
        }
        return payAccTypeRepository.save(updateData);         
    }
}
