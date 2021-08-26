package com.mnit.erp.finance.claim.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.finance.claim.model.Claim;
import com.mnit.erp.finance.claim.model.ClaimBehaviour;
import com.mnit.erp.finance.claim.service.ClaimService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains claim rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 13 July, 2021
 */
@RestController
@RequestMapping("/claim")
public class ClaimController extends AbstractController {

    @Autowired
    ClaimService claimService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody Claim claim){
        Claim claim1 = this.claimService.add(claim);
        ResponseMessageType responseMessageType = Objects.nonNull(claim1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claim1) ? "Claim added successfully!" : "Unable to add claim!")
                .messageType(responseMessageType)
                .response(claim1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody Claim claim){
        Claim claim1 = this.claimService.update(claim);
        ResponseMessageType responseMessageType = Objects.nonNull(claim1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claim1) ? "Claim updated successfully!" : "Unable to update claim!")
                .messageType(responseMessageType)
                .response(claim1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Claim claim1 = this.claimService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(claim1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claim1) ? "Claim found successfully!" : "Unable to find claim!")
                .messageType(responseMessageType)
                .response(claim1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Claim> claims = this.claimService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(claims) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claims) && !claims.isEmpty() ? "Claims found!" : "Unable to find claims!")
                .messageType(responseMessageType)
                .response(claims).build();
    }
    @GetMapping("/findAllUnderClaimBehaviour/{claimBehaviour}")
    public CustomResponseMessage findAllUnderClaimBehaviour(@PathVariable ClaimBehaviour claimBehaviour){
        List<Claim> claims = this.claimService.findAllUnderClaimBehaviour(claimBehaviour);
        ResponseMessageType responseMessageType = Objects.nonNull(claims) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claims) && !claims.isEmpty() ? "Claims found!" : "Unable to find claims!")
                .messageType(responseMessageType)
                .response(claims).build();
    }

}
