package com.mnit.erp.country.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.country.model.Country;
import com.mnit.erp.country.service.CountryService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/country")
public class CountryController extends AbstractController {

    @Autowired
    CountryService countryService;

    @PostMapping
    public CustomResponseMessage add(@RequestBody Country country){
        Country country1 = this.countryService.add(country);
        ResponseMessageType responseMessageType = Objects.nonNull(country1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(country1) ? "Country added successfully! !" : "Unable to add country!")
                .messageType(responseMessageType)
                .response(country1).build();
    }

    @PutMapping
    public CustomResponseMessage update(@RequestBody Country country){
        Country country1 = this.countryService.update(country);
        ResponseMessageType responseMessageType = Objects.nonNull(country1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(country1) ? "Country updated successfully! !" : "Unable to update country!")
                .messageType(responseMessageType)
                .response(country1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Country country1 = this.countryService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(country1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(country1) ? "Country found successfully! !" : "Unable to find country with id!")
                .messageType(responseMessageType)
                .response(country1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Country> countries = this.countryService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(countries) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(countries) && countries.size() > 0 ? "Countries found!" : "No records found in country data!")
                .messageType(responseMessageType)
                .response(countries).build();
    }

}
