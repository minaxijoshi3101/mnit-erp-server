package com.mnit.erp.finance.claimActivity.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.finance.claimActivity.model.ClaimActivity;
import com.mnit.erp.finance.claimActivity.service.ClaimActivityService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains claim activity rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 14 July, 2021
 */
@RestController
@RequestMapping("/claim-activity")
public class ClaimActivityController extends AbstractController {

    @Autowired
    ClaimActivityService claimActivityService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody ClaimActivity claimActivity){
        ClaimActivity claimActivity1 = this.claimActivityService.add(claimActivity);
        ResponseMessageType responseMessageType = Objects.nonNull(claimActivity1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claimActivity1) ? "Claim activity added successfully!" : "Unable to add claim activity!")
                .messageType(responseMessageType)
                .response(claimActivity1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody ClaimActivity claimActivity){
        ClaimActivity claimActivity1 = this.claimActivityService.update(claimActivity);
        ResponseMessageType responseMessageType = Objects.nonNull(claimActivity1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claimActivity1) ? "Claim activity updated successfully!" : "Unable to update claim activity !")
                .messageType(responseMessageType)
                .response(claimActivity1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        ClaimActivity claimActivity = this.claimActivityService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(claimActivity) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claimActivity) ? "Claim activity found successfully!" : "Unable to find claim activity!")
                .messageType(responseMessageType)
                .response(claimActivity).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<ClaimActivity> claimActivities = this.claimActivityService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(claimActivities) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claimActivities) && !claimActivities.isEmpty() ? "Claim activities found!" : "Unable to find claim activities!")
                .messageType(responseMessageType)
                .response(claimActivities).build();
    }

}
