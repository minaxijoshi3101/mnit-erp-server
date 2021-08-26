package com.mnit.erp.state.service;

import com.mnit.erp.country.service.CountryService;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.state.model.State;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StateService {

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CountryService countryService;

    public State add(State state){
        State state1 = this.stateRepository.findByName(state.getName());
        if(Objects.nonNull(state1)){
            throw new ServiceException("State : " + state.getName() + " already exists. Can't add new!");
        }
        if(this.validate(state)){
            return  this.stateRepository.save(state);
        }
        return  null;
    }

    public State update(State state){
        State state1 = this.stateRepository.findById(state.getId()).orElse(null);
        if(Objects.isNull(state1)){
            throw new ServiceException("State not found. Can't update");
        }
        if(this.validate(state)){
            CommonUtils.copyNonNullProperties(state, state1);
            return this.stateRepository.save(state);
        }
        return null;
    }

    public State find(Long id){
        State state = this.stateRepository.findById(id).orElse(null);
        //state.setCountry(this.countryService.find(state.getCountryId()));
        return state;
    }

    public List<State> findAll(){
        List<State> states = this.stateRepository.findAll();
        //states.forEach(state -> state.setCountry(this.countryService.find(state.getCountryId())));
        return states;
    }

    private boolean validate(State state){
        return  true;
    }

    public List<State> findAllInCountry(Long countryId) {
        return this.stateRepository.findByCountry(countryService.find(countryId));
    }
}
