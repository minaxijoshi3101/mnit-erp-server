package com.mnit.erp.hr.service;

import com.mnit.erp.hr.repository.GradePayRepository;
import com.mnit.erp.hr.model.GradePay;
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
public class GradePayService {
    @Autowired
    GradePayRepository gradePayRepository;
    
    public List<GradePay> getAll() {
        List<GradePay> dataList = new ArrayList<>();
        gradePayRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public GradePay getById(long id) {
        return gradePayRepository.findById(id).orElse(null);
    }
    
    public GradePay create(GradePay createData) {  
        GradePay thisGradePay = gradePayRepository.findByName(createData.getName()).orElse(null);
        if(Objects.nonNull(thisGradePay)){
            throw new ServiceException("Duplicate Grade Pay. Can't add!");
        }
        return gradePayRepository.save(createData);
    }
    
    public GradePay update(GradePay updateData) {
        GradePay thisGradePay = gradePayRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisGradePay)){
            throw new ServiceException("Grade pay not found. Can't update!");
        }
        GradePay checkGradePay = gradePayRepository.findByName(updateData.getName()).orElse(null);
        if(Objects.nonNull(checkGradePay) && !Objects.equals(updateData.getId(), checkGradePay.getId())){
            throw new ServiceException("Duplicate Grade Pay. Can't update!");
        }
        
        return gradePayRepository.save(updateData); 
    }
}
