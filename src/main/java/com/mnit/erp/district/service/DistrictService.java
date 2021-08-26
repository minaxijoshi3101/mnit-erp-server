package com.mnit.erp.district.service;

import com.mnit.erp.district.model.District;
import com.mnit.erp.district.repository.DistrictRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.state.model.State;
import com.mnit.erp.state.service.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    StateRepository stateRepository;

    public District add(District district){
        District byName = this.districtRepository.findByName(district.getName());
        if(Objects.nonNull(byName)){
            throw new ServiceException("District name already exists. Can't add again!");
        }
        return validate( district) ? this.districtRepository.save(district) : null;
    }

    public District findById(Long id){
        return this.districtRepository.findById(id).orElse(null);
    }

    public List<District> findAll(){
        return this.districtRepository.findAll();
    }

    public List<District> findAllUnderState(Long stateId){
        State state = this.stateRepository.findById(stateId).orElse(null);
        if(Objects.nonNull(state)){
            return this.districtRepository.findByState(state);
        }
        return null;
    }

    private boolean validate(District district){
        return true;
    }

}
