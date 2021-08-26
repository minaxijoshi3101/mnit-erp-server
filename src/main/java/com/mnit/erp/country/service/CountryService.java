package com.mnit.erp.country.service;

import com.mnit.erp.country.model.Country;
import com.mnit.erp.country.repository.CountryRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public Country add(Country country){
        Country country1 = this.countryRepository.findByName(country.getName());
        if(Objects.nonNull(country1)){
            throw new ServiceException("Country : " + country.getName() + " already exists. Can't add new!");
        }
        if(this.validate(country)){
            return this.countryRepository.save(country);
        }
        return null;
    }

    public Country update(Country country){
        Country country1 = this.countryRepository.findById(country.getId()).orElse(null);
        if(Objects.isNull(country1)){
            throw new ServiceException("Country not found. Can't update!");
        }
        if(this.validate(country)){
            CommonUtils.copyNonNullProperties(country, country1);
            return this.countryRepository.save(country);
        }
        return null;
    }

    public Country find(Long id){
        return this.countryRepository.findById(id).orElse(null);
    }

    public List<Country> findAll(){
        return this.countryRepository.findAll();
    }

    private boolean validate(Country country){
        return true;
    }

}
