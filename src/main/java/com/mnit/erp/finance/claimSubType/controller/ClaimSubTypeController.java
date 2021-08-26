package com.mnit.erp.finance.claimSubType.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.finance.claimSubType.model.ClaimSubType;
import com.mnit.erp.finance.claimSubType.service.ClaimSubTypeService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains claim sub type rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 13 July, 2021
 */
@RestController
@RequestMapping("/claim-sub-type")
public class ClaimSubTypeController extends AbstractController {

    @Autowired
    ClaimSubTypeService claimSubTypeService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody ClaimSubType claimSubType){
        ClaimSubType claimSubType1 = this.claimSubTypeService.add(claimSubType);
        ResponseMessageType responseMessageType = Objects.nonNull(claimSubType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claimSubType1) ? "Claim sub type added successfully!" : "Unable to add claim sub type!")
                .messageType(responseMessageType)
                .response(claimSubType1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody ClaimSubType claimSubType){
        ClaimSubType claimSubType1 = this.claimSubTypeService.update(claimSubType);
        ResponseMessageType responseMessageType = Objects.nonNull(claimSubType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claimSubType1) ? "Claim sub type updated successfully!" : "Unable to update claim sub type !")
                .messageType(responseMessageType)
                .response(claimSubType1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        ClaimSubType claimSubType = this.claimSubTypeService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(claimSubType) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claimSubType) ? "Claim sub type found successfully!" : "Unable to find claim sub type!")
                .messageType(responseMessageType)
                .response(claimSubType).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<ClaimSubType> claimSubTypes = this.claimSubTypeService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(claimSubTypes) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claimSubTypes) && !claimSubTypes.isEmpty() ? "Claim sub type found!" : "Unable to find claim sub types!")
                .messageType(responseMessageType)
                .response(claimSubTypes).build();
    }

    @GetMapping("/findAllUnderClaim/{claimId}")
    public CustomResponseMessage findAllUnderClaim(@PathVariable Long claimId){
        List<ClaimSubType> claimSubTypes = this.claimSubTypeService.findAllUnderClaim(claimId);
        ResponseMessageType responseMessageType = Objects.nonNull(claimSubTypes) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claimSubTypes) && !claimSubTypes.isEmpty() ? "Claim sub type found!" : "Unable to find claim sub types!")
                .messageType(responseMessageType)
                .response(claimSubTypes).build();
    }

}
