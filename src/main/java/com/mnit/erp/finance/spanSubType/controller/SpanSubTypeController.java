package com.mnit.erp.finance.spanSubType.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.finance.blockSubType.model.BlockSubType;
import com.mnit.erp.finance.spanSubType.service.SpanSubTypeService;
import com.mnit.erp.finance.spanSubType.model.SpanSubType;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains span sub type rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 19 July, 2021
 */
@RestController
@RequestMapping("/span-sub-type")
public class SpanSubTypeController extends AbstractController {

    @Autowired
    SpanSubTypeService spanSubTypeService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody SpanSubType spanSubType){
        SpanSubType spanSubType1 = this.spanSubTypeService.add(spanSubType);
        ResponseMessageType responseMessageType = Objects.nonNull(spanSubType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(spanSubType1) ? "Span sub type added successfully!" : "Unable to add span sub type!")
                .messageType(responseMessageType)
                .response(spanSubType1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody SpanSubType spanSubType){
        SpanSubType spanSubType1 = this.spanSubTypeService.update(spanSubType);
        ResponseMessageType responseMessageType = Objects.nonNull(spanSubType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(spanSubType1) ? "Span sub type updated successfully!" : "Unable to update span sub type!")
                .messageType(responseMessageType)
                .response(spanSubType1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        SpanSubType spanSubType = this.spanSubTypeService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(spanSubType) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(spanSubType) ? "Span sub type found successfully!" : "Unable to find span sub type!")
                .messageType(responseMessageType)
                .response(spanSubType).build();
    }

    @GetMapping("/findByClaimBehaviourSetting/{claimBehaviourSettingId}")
    public CustomResponseMessage findAll(@PathVariable Long claimBehaviourSettingId){
        List<SpanSubType> spanSubTypeList = this.spanSubTypeService.findByClaimBehaviourSetting(claimBehaviourSettingId);
        ResponseMessageType responseMessageType = Objects.nonNull(spanSubTypeList) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(spanSubTypeList) && !spanSubTypeList.isEmpty() ? "Block sub types found!" : "Unable to find block sub types!")
                .messageType(responseMessageType)
                .response(spanSubTypeList).build();
    }

}
