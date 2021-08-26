package com.mnit.erp.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.hr.repository.EmployeeStatusRepository;
import com.mnit.erp.hr.model.EmployeeStatus;
import com.mnit.erp.exceptions.ServiceException;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Prahalad
 */
@Service
public class EmployeeStatusService {
    @Autowired
    EmployeeStatusRepository employeeStatusRepository;
    
    public List<EmployeeStatus> getAll() {
        List<EmployeeStatus> dataList = new ArrayList<>();
        employeeStatusRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public EmployeeStatus getById(long id) {
        return employeeStatusRepository.findById(id).orElse(null);
    }
    
    public EmployeeStatus create(EmployeeStatus createData) {   
        EmployeeStatus thisEmployeeStatus = employeeStatusRepository.findByName(createData.getName()).orElse(null);
        if(Objects.nonNull(thisEmployeeStatus)){
            throw new ServiceException("Duplicate EmployeeStatus. Can't add!");
        }
        return employeeStatusRepository.save(createData);
    }
    
    public EmployeeStatus update(EmployeeStatus updateData) {
        EmployeeStatus thisEmployeeStatus = employeeStatusRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisEmployeeStatus)){
            throw new ServiceException("EmployeeStatus not found. Can't update!");
        }
        EmployeeStatus checkEmployeeStatus = employeeStatusRepository.findByName(updateData.getName()).orElse(null);
        if(Objects.nonNull(checkEmployeeStatus) && !Objects.equals(updateData.getId(), checkEmployeeStatus.getId())){
            throw new ServiceException("Duplicate EmployeeStatus. Can't update!");
        }
        return employeeStatusRepository.save(updateData);         
    }
}
