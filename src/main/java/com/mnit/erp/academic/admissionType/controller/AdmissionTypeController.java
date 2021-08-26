package com.mnit.erp.academic.admissionType.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.admissionType.model.AdmissionType;
import com.mnit.erp.academic.admissionType.service.AdmissionTypeService;
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
@RequestMapping("/admissionType")
public class AdmissionTypeController extends AbstractController {

    @Autowired
    AdmissionTypeService admissionTypeService;

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        AdmissionType admissionType = this.admissionTypeService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(admissionType) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(admissionType) ? "Admission Type found in masters!" : "Unable to find admission type in masters!")
                .messageType(responseMessageType)
                .response(admissionType).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<AdmissionType> admissionTypes = this.admissionTypeService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(admissionTypes) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(admissionTypes) && admissionTypes.size() > 0 ? "Category found in masters!" : "Unable to find category in masters!")
                .messageType(responseMessageType)
                .response(admissionTypes).build();
    }

}
