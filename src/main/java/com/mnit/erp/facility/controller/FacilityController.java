package com.mnit.erp.facility.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.facility.model.Facility;
import com.mnit.erp.facility.service.FacilityService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains Facility rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 16 June, 2021
 */
@RestController
@RequestMapping("/facility")
public class FacilityController extends AbstractController {

    @Autowired
    FacilityService facilityService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody Facility facility){
        Facility facility1 = this.facilityService.add(facility);
        ResponseMessageType responseMessageType = Objects.nonNull(facility1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(facility1) ? "Facility added successfully in masters!" : "Unable to add facility in masters!")
                .messageType(responseMessageType)
                .response(facility1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody Facility facility){
        Facility facility1 = this.facilityService.update(facility);
        ResponseMessageType responseMessageType = Objects.nonNull(facility1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(facility1) ? "Facility updated successfully in masters!" : "Unable to update facility in masters!")
                .messageType(responseMessageType)
                .response(facility1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Facility facility1 = this.facilityService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(facility1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(facility1) ? "Facility found successfully in masters!" : "Unable to find facility in masters!")
                .messageType(responseMessageType)
                .response(facility1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Facility> facilities = this.facilityService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(facilities) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(facilities) && !facilities.isEmpty() ? "Facilitys found successfully in masters!" : "Unable to find facilitys in masters!")
                .messageType(responseMessageType)
                .response(facilities).build();
    }

}
