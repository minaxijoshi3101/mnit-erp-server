package com.mnit.erp.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.hr.repository.CadreRepository;
import com.mnit.erp.hr.model.Cadre;
import com.mnit.erp.exceptions.ServiceException;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Prahalad
 */
@Service
public class CadreService {
    @Autowired
    CadreRepository cadreRepository;
    
    public List<Cadre> getAll() {
        List<Cadre> dataList = new ArrayList<>();
        cadreRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public Cadre getById(long id) {
        return cadreRepository.findById(id).orElse(null);
    }
    
    public Cadre create(Cadre createData) {   
        Cadre thisCadre = cadreRepository.findByName(createData.getName()).orElse(null);
        if(Objects.nonNull(thisCadre)){
            throw new ServiceException("Duplicate Cadre. Can't add!");
        }
        return cadreRepository.save(createData);
    }
    
    public Cadre update(Cadre updateData) {
        Cadre thisCadre = cadreRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisCadre)){
            throw new ServiceException("Cadre not found. Can't update!");
        }
        Cadre checkCadre = cadreRepository.findByName(updateData.getName()).orElse(null);
        if(Objects.nonNull(checkCadre) && !Objects.equals(updateData.getId(), checkCadre.getId())){
            throw new ServiceException("Duplicate Cadre. Can't update!");
        }
        return cadreRepository.save(updateData);         
    }
}
