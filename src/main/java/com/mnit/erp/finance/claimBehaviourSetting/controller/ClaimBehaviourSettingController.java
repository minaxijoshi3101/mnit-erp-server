package com.mnit.erp.finance.claimBehaviourSetting.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.finance.claimBehaviourSetting.model.ClaimBehaviourSetting;
import com.mnit.erp.finance.claimBehaviourSetting.service.ClaimBehaviourSettingService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains claim behaviour setting rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 16 July, 2021
 */
@RestController
@RequestMapping("/claim-behaviour-setting")
public class ClaimBehaviourSettingController extends AbstractController {

    @Autowired
    ClaimBehaviourSettingService claimBehaviourSettingService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody ClaimBehaviourSetting claimBehaviourSetting){
        ClaimBehaviourSetting claimBehaviourSetting1 = this.claimBehaviourSettingService.add(claimBehaviourSetting);
        ResponseMessageType responseMessageType = Objects.nonNull(claimBehaviourSetting1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claimBehaviourSetting1) ? "Claim behaviour setting added successfully!" : "Unable to add claim behaviour setting!")
                .messageType(responseMessageType)
                .response(claimBehaviourSetting1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody ClaimBehaviourSetting claimBehaviourSetting){
        ClaimBehaviourSetting claimBehaviourSetting1 = this.claimBehaviourSettingService.update(claimBehaviourSetting);
        ResponseMessageType responseMessageType = Objects.nonNull(claimBehaviourSetting1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claimBehaviourSetting1) ? "Claim behaviour setting updated successfully!" : "Unable to update claim behaviour setting!")
                .messageType(responseMessageType)
                .response(claimBehaviourSetting1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        ClaimBehaviourSetting claimBehaviourSetting = this.claimBehaviourSettingService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(claimBehaviourSetting) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claimBehaviourSetting) ? "Claim behaviour setting found successfully!" : "Unable to find claim behaviour setting!")
                .messageType(responseMessageType)
                .response(claimBehaviourSetting).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<ClaimBehaviourSetting> claimBehaviourSettings = this.claimBehaviourSettingService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(claimBehaviourSettings) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(claimBehaviourSettings) && !claimBehaviourSettings.isEmpty() ? "Claim behaviour settings found!" : "Unable to find claim behaviour settings!")
                .messageType(responseMessageType)
                .response(claimBehaviourSettings).build();
    }

}
