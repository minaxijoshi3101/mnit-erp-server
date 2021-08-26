package com.mnit.erp.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.hr.repository.AppointmentNatureRepository;
import com.mnit.erp.hr.model.AppointmentNature;
import com.mnit.erp.exceptions.ServiceException;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Prahalad
 */
@Service
public class AppointmentNatureService {
    @Autowired
    AppointmentNatureRepository appointmentNatureRepository;
    
    public List<AppointmentNature> getAll() {
        List<AppointmentNature> dataList = new ArrayList<>();
        appointmentNatureRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public AppointmentNature getById(long id) {
        return appointmentNatureRepository.findById(id).orElse(null);
    }
    
    public AppointmentNature create(AppointmentNature createData) {   
        AppointmentNature thisAppointmentNature = appointmentNatureRepository.findByName(createData.getName()).orElse(null);
        if(Objects.nonNull(thisAppointmentNature)){
            throw new ServiceException("Duplicate Nature of Appointment. Can't add!");
        }
        return appointmentNatureRepository.save(createData);
    }
    
    public AppointmentNature update(AppointmentNature updateData) {
        AppointmentNature thisAppointmentNature = appointmentNatureRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisAppointmentNature)){
            throw new ServiceException("Nature of Appointment not found. Can't update!");
        }
        AppointmentNature checkAppointmentNature = appointmentNatureRepository.findByName(updateData.getName()).orElse(null);
        if(Objects.nonNull(checkAppointmentNature) && !Objects.equals(updateData.getId(), checkAppointmentNature.getId())){
            throw new ServiceException("Duplicate Nature of Appointment. Can't update!");
        }
        return appointmentNatureRepository.save(updateData);         
    }
}
