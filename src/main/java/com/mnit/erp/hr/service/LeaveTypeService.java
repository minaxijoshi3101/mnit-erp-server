package com.mnit.erp.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.hr.repository.LeaveTypeRepository;
import com.mnit.erp.hr.model.LeaveType;
import com.mnit.erp.exceptions.ServiceException;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Prahalad
 */
@Service
public class LeaveTypeService {
    @Autowired
    LeaveTypeRepository leaveTypeRepository;
    
    public List<LeaveType> getAll() {
        List<LeaveType> dataList = new ArrayList<>();
        leaveTypeRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public LeaveType getById(long id) {
        return leaveTypeRepository.findById(id).orElse(null);
    }
    
    public LeaveType create(LeaveType createData) {   
        LeaveType thisLeaveType = leaveTypeRepository.findByName(createData.getName()).orElse(null);
        if(Objects.nonNull(thisLeaveType)){
            throw new ServiceException("Duplicate Leave Type Name. Can't add!");
        }
        
        LeaveType thisLeaveType2 = leaveTypeRepository.findByAbbreviation(createData.getAbbreviation()).orElse(null);
        if(Objects.nonNull(thisLeaveType2)){
            throw new ServiceException("Duplicate Leave Type Abbreviation. Can't add!");
        }
        
        return leaveTypeRepository.save(createData);
    }
    
    public LeaveType update(LeaveType updateData) {
        LeaveType thisLeaveType = leaveTypeRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisLeaveType)){
            throw new ServiceException("Leave Type not found. Can't update!");
        }
        
        LeaveType checkLeaveType = leaveTypeRepository.findByName(updateData.getName()).orElse(null);
        if(Objects.nonNull(checkLeaveType) && !Objects.equals(updateData.getId(), checkLeaveType.getId())){
            throw new ServiceException("Duplicate Leave Type Name. Can't update!");
        }
        
        LeaveType checkLeaveType2 = leaveTypeRepository.findByAbbreviation(updateData.getAbbreviation()).orElse(null);
        if(Objects.nonNull(checkLeaveType2) && !Objects.equals(updateData.getId(), checkLeaveType2.getId())){
            throw new ServiceException("Duplicate Leave Type Abbreviation. Can't update!");
        }
        return leaveTypeRepository.save(updateData);         
    }
}
