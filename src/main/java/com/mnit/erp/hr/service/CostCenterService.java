package com.mnit.erp.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.hr.repository.CostCenterRepository;
import com.mnit.erp.hr.model.CostCenter;
import com.mnit.erp.exceptions.ServiceException;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Prahalad
 */
@Service
public class CostCenterService {
    @Autowired
    CostCenterRepository costCenterRepository;
    
    public List<CostCenter> getAll() {
        List<CostCenter> dataList = new ArrayList<>();
        costCenterRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public CostCenter getById(long id) {
        return costCenterRepository.findById(id).orElse(null);
    }
    
    public CostCenter create(CostCenter createData) {   
        CostCenter thisCostCenter = costCenterRepository.findByName(createData.getName()).orElse(null);
        if(Objects.nonNull(thisCostCenter)){
            throw new ServiceException("Duplicate Cost Center. Can't add!");
        }
        return costCenterRepository.save(createData);
    }
    
    public CostCenter update(CostCenter updateData) {
        CostCenter thisCostCenter = costCenterRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisCostCenter)){
            throw new ServiceException("Cost Center not found. Can't update!");
        }
        CostCenter checkCostCenter = costCenterRepository.findByName(updateData.getName()).orElse(null);
        if(Objects.nonNull(checkCostCenter) && !Objects.equals(updateData.getId(), checkCostCenter.getId())){
            throw new ServiceException("Duplicate Cost Center. Can't update!");
        }
        return costCenterRepository.save(updateData);         
    }
}
