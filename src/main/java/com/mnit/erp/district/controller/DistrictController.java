package com.mnit.erp.district.controller;

import com.mnit.erp.district.model.District;
import com.mnit.erp.district.service.DistrictService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    DistrictService districtService;

    @GetMapping("/findAll")
    public CustomResponseMessage findAll(){
        List<District> districts = this.districtService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(districts) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(districts) && !districts.isEmpty() ? "Districts found successfully in masters!" : "Unable to find districts in masters!")
                .messageType(responseMessageType)
                .response(districts).build();
    }

    @GetMapping("/findById/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
        District district = this.districtService.findById(id);
        ResponseMessageType responseMessageType = Objects.nonNull(district) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(district) ? "Districts found successfully in masters!" : "Unable to find districts in masters!")
                .messageType(responseMessageType)
                .response(district).build();
    }


    @GetMapping("/findAllUnderState/{stateId}")
    public CustomResponseMessage findAllUnderState(@PathVariable Long stateId){
        List<District> districts = this.districtService.findAllUnderState(stateId);
        ResponseMessageType responseMessageType = Objects.nonNull(districts) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(districts) && !districts.isEmpty() ? "Districts under state found successfully in masters!" : "Unable to find districts under state in masters!")
                .messageType(responseMessageType)
                .response(districts).build();
    }

}
