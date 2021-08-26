package com.mnit.erp.hr.service;

import com.mnit.erp.hr.model.PayBand;
import com.mnit.erp.hr.repository.PayBandRepository;
import com.mnit.erp.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prahalad
 */
@Service
public class PayBandService {
    @Autowired
    PayBandRepository payBandRepository;
    
    public List<PayBand> getAll() {
        List<PayBand> dataList = new ArrayList<>();
        payBandRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public PayBand getById(long id) {
        return payBandRepository.findById(id).orElse(null);
    }
    
    public PayBand create(PayBand createData) {   
        PayBand thisPayBand = payBandRepository.findByName(createData.getName()).orElse(null);
        if(Objects.nonNull(thisPayBand)){
            throw new ServiceException("Duplicate Pay Band. Can't add!");
        }
        return payBandRepository.save(createData);
    }
    
    public PayBand update(PayBand updateData) {
        PayBand thisPayBand = payBandRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisPayBand)){
            throw new ServiceException("Pay Band not found. Can't update!");
        }
        PayBand checkPayBand = payBandRepository.findByName(updateData.getName()).orElse(null);
        if(Objects.nonNull(checkPayBand) && !Objects.equals(updateData.getId(), checkPayBand.getId())){
            throw new ServiceException("Duplicate Pay Band. Can't update!");
        }
        return payBandRepository.save(updateData);         
    }
}
