package com.mnit.erp.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.hr.repository.HostelRepository;
import com.mnit.erp.hr.model.Hostel;
import com.mnit.erp.exceptions.ServiceException;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Prahalad
 */
@Service
public class HostelService {
    @Autowired
    HostelRepository hostelRepository;
    
    public List<Hostel> getAll() {
        List<Hostel> dataList = new ArrayList<>();
        hostelRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public Hostel getById(long id) {
        return hostelRepository.findById(id).orElse(null);
    }
    
    public Hostel create(Hostel createData) {   
        Hostel thisHostel = hostelRepository.findByName(createData.getName()).orElse(null);
        if(Objects.nonNull(thisHostel)){
            throw new ServiceException("Duplicate Hostel. Can't add!");
        }
        return hostelRepository.save(createData);
    }
    
    public Hostel update(Hostel updateData) {
        Hostel thisHostel = hostelRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisHostel)){
            throw new ServiceException("Hostel not found. Can't update!");
        }
        Hostel checkHostel = hostelRepository.findByName(updateData.getName()).orElse(null);
        if(Objects.nonNull(checkHostel) && !Objects.equals(updateData.getId(), checkHostel.getId())){
            throw new ServiceException("Duplicate Hostel. Can't update!");
        }
        return hostelRepository.save(updateData);         
    }
}
