package com.mnit.erp.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.hr.model.LeaveRecords;
import com.mnit.erp.hr.repository.LeaveRecordsRepository;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Prahalad
 */
@Service
public class LeaveRecordsService {
    @Autowired
    LeaveRecordsRepository leaveRecordsRepository;
    
    public List<LeaveRecords> getAll() {
        List<LeaveRecords> dataList = new ArrayList<>();
        leaveRecordsRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public LeaveRecords getById(long id) {
        return leaveRecordsRepository.findById(id).orElse(null);
    }
    
    public LeaveRecords create(LeaveRecords createData) {   
        return leaveRecordsRepository.save(createData);
    }
    
    public LeaveRecords update(LeaveRecords updateData) {
        return leaveRecordsRepository.save(updateData);
    }
}
