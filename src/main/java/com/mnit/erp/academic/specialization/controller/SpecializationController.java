package com.mnit.erp.academic.specialization.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.specialization.model.Specialization;
import com.mnit.erp.academic.specialization.service.SpecializationSerivce;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/specialization")
public class SpecializationController extends AbstractController {

    @Autowired
    SpecializationSerivce specializationSerivce;

    @PostMapping
    public CustomResponseMessage add(@RequestBody  Specialization specialization){
        Specialization specialization1 = this.specializationSerivce.add(specialization);
        ResponseMessageType responseMessageType = Objects.nonNull(specialization1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(specialization1) ? "Specialization added successfully! !" : "Unable to add specialization!")
                .messageType(responseMessageType)
                .response(specialization1).build();
    }

    @PutMapping
    public CustomResponseMessage update(@RequestBody  Specialization specialization){
        Specialization specialization1 = this.specializationSerivce.update(specialization);
        ResponseMessageType responseMessageType = Objects.nonNull(specialization1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(specialization1) ? "Specialization updated successfully! !" : "Unable to update specialization!")
                .messageType(responseMessageType)
                .response(specialization1).build();
    }

    @GetMapping("/findAllUnderBranch/{branchId}")
    public CustomResponseMessage findAllUnderBranch(@PathVariable Long branchId){
        List<Specialization> specializations = this.specializationSerivce.findAllUnderBranch(branchId);
        ResponseMessageType responseMessageType = Objects.nonNull(specializations) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(specializations) ? "Specializations found under branch! !" : "Unable to find specializations data under branch!")
                .messageType(responseMessageType)
                .response(specializations).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Specialization specialization1 = this.specializationSerivce.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(specialization1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(specialization1) ? "Specialization found successfully! !" : "Unable to find specialization!")
                .messageType(responseMessageType)
                .response(specialization1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Specialization> specializations = this.specializationSerivce.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(specializations) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(specializations) ? "Specializations found! !" : "Unable to find specializations data!")
                .messageType(responseMessageType)
                .response(specializations).build();
    }

}
