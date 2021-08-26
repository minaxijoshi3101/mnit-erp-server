package com.mnit.erp.finance.blockSubType.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.finance.blockSubType.service.BlockSubTypeService;
import com.mnit.erp.finance.blockSubType.model.BlockSubType;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains block sub type rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 19 July, 2021
 */
@RestController
@RequestMapping("/block-sub-type")
public class BlockSubTypeController extends AbstractController {

    @Autowired
    BlockSubTypeService blockSubTypeService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody BlockSubType blockSubType){
        BlockSubType blockSubType1 = this.blockSubTypeService.add(blockSubType);
        ResponseMessageType responseMessageType = Objects.nonNull(blockSubType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(blockSubType1) ? "Block sub type added successfully!" : "Unable to add block sub type!")
                .messageType(responseMessageType)
                .response(blockSubType1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody BlockSubType blockSubType){
        BlockSubType blockSubType1 = this.blockSubTypeService.update(blockSubType);
        ResponseMessageType responseMessageType = Objects.nonNull(blockSubType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(blockSubType1) ? "Block sub type updated successfully!" : "Unable to update block sub type!")
                .messageType(responseMessageType)
                .response(blockSubType1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        BlockSubType blockSubType = this.blockSubTypeService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(blockSubType) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(blockSubType) ? "Block sub type found successfully!" : "Unable to find block sub type!")
                .messageType(responseMessageType)
                .response(blockSubType).build();
    }
    @GetMapping("/findByClaimBehaviourSetting/{claimBehaviourSettingId}")
    public CustomResponseMessage findAll(@PathVariable Long claimBehaviourSettingId){
        List<BlockSubType> blockSubTypes = this.blockSubTypeService.findByClaimBehaviourSetting(claimBehaviourSettingId);
        ResponseMessageType responseMessageType = Objects.nonNull(blockSubTypes) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(blockSubTypes) && !blockSubTypes.isEmpty() ? "Block sub types found!" : "Unable to find block sub types!")
                .messageType(responseMessageType)
                .response(blockSubTypes).build();
    }

}
