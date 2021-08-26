package com.mnit.erp.hr.controller;

import com.mnit.erp.hr.service.GradePayService;
import com.mnit.erp.hr.model.GradePay;

import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Prahalad
 */
@RestController
@RequestMapping("/gradepay")
public class GradePayController {
    @Autowired
    GradePayService gradePayService;
    
    @GetMapping
    public CustomResponseMessage findAll(){
        List<GradePay> dataList = gradePayService.getAll();
        ResponseMessageType responseMessageType = Objects.nonNull(dataList) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(dataList) && dataList.size() > 0 ? "Success! Data is fetched." : "No record found.")
                .messageType(responseMessageType)
                .response(dataList).build();
    }
    
    @GetMapping("/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
        GradePay thisData = gradePayService.getById(id);
        ResponseMessageType responseMessageType = Objects.nonNull(thisData) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(thisData) ? "Success! Data is fetched." : "No record found.")
                .messageType(responseMessageType)
                .response(thisData).build();
    }
    
    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody GradePay createData){
        GradePay gradePay = null;
        try {
            gradePay = gradePayService.create(createData);
        } catch (Exception e) {
            ResponseMessageType responseMessageType = ResponseMessageType.ERROR;
            return CustomResponseMessage.builder().message("Error! "+e.getMessage())
                    .messageType(responseMessageType)
                    .response(null).build();
        }
        
        ResponseMessageType responseMessageType = Objects.nonNull(gradePay) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(gradePay) ? "Success! Data is Saved." : "Unable to add data!")
                .messageType(responseMessageType)
                .response(gradePay).build();
    }
    
    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody GradePay updateData){
        GradePay gradePay = null;
        try {
            gradePay = gradePayService.update(updateData);
        } catch (Exception e) {
            ResponseMessageType responseMessageType = ResponseMessageType.ERROR;
            return CustomResponseMessage.builder().message("Error! "+e.getMessage())
                    .messageType(responseMessageType)
                    .response(null).build();
        }
        
        ResponseMessageType responseMessageType = Objects.nonNull(gradePay) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(gradePay) ? "Success! Data is Saved." : "Unable to update data!")
                .messageType(responseMessageType)
                .response(gradePay).build();
    }
}
