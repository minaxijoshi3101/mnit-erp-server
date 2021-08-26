package com.mnit.erp.religion.controller;

import com.mnit.erp.religion.service.ReligionService;
import com.mnit.erp.religion.model.Religion;

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
@RequestMapping("/religion")
public class ReligionController {
    @Autowired
    ReligionService religionService;
    
    @GetMapping
    public CustomResponseMessage findAll(){
        List<Religion> dataList = religionService.getAll();
        ResponseMessageType responseMessageType = Objects.nonNull(dataList) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(dataList) && dataList.size() > 0 ? "Success! Data is fetched." : "No record found.")
                .messageType(responseMessageType)
                .response(dataList).build();
    }
    
    @GetMapping("/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
        Religion thisData = religionService.getById(id);
        ResponseMessageType responseMessageType = Objects.nonNull(thisData) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(thisData) ? "Success! Data is fetched." : "No record found.")
                .messageType(responseMessageType)
                .response(thisData).build();
    }
    
    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody Religion createData){
        Religion religion = null;
        try {
            religion = religionService.create(createData);
        } catch (Exception e) {
            ResponseMessageType responseMessageType = ResponseMessageType.ERROR;
            return CustomResponseMessage.builder().message("Error! "+e.getMessage())
                    .messageType(responseMessageType)
                    .response(null).build();
        }
        
        ResponseMessageType responseMessageType = Objects.nonNull(religion) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(religion) ? "Success! Data is Saved." : "Unable to add data!")
                .messageType(responseMessageType)
                .response(religion).build();
    }
    
    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody Religion updateData){
        Religion religion = null;
        try {
            religion = religionService.update(updateData);
        } catch (Exception e) {
            ResponseMessageType responseMessageType = ResponseMessageType.ERROR;
            return CustomResponseMessage.builder().message("Error! "+e.getMessage())
                    .messageType(responseMessageType)
                    .response(null).build();
        }
 
        ResponseMessageType responseMessageType = Objects.nonNull(religion) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(religion) ? "Success! Data is Saved." : "Unable to update data!")
                .messageType(responseMessageType)
                .response(religion).build();
    }
}

