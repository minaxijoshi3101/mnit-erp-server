package com.mnit.erp.designation.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.designation.model.Designation;
import com.mnit.erp.designation.service.DesignationService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/designation")
public class DesignationController extends AbstractController {

    @Autowired
    public DesignationService designationService;

    @PostMapping
    public CustomResponseMessage add(@RequestBody Designation designation){
        Designation designation1 = this.designationService.add(designation);
        ResponseMessageType responseMessageType = Objects.nonNull(designation1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(designation1) ? "Designation added successfully! !" : "Unable to add designation!")
                .messageType(responseMessageType)
                .response(designation1).build();
    }

    @PutMapping
    public CustomResponseMessage update(@RequestBody Designation designation){
        Designation designation1 = this.designationService.update(designation);
        ResponseMessageType responseMessageType = Objects.nonNull(designation1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(designation1) ? "Designation updated successfully! !" : "Unable to update designation!")
                .messageType(responseMessageType)
                .response(designation1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Designation designation = this.designationService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(designation) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(designation) ? "Designation found! !" : "Unable to find designation with given id!")
                .messageType(responseMessageType)
                .response(designation).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Designation> designations = this.designationService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(designations) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(designations) && designations.size() > 0 ? "Designation found! !" : "Unable to find designation!")
                .messageType(responseMessageType)
                .response(designations).build();
    }

}
