package com.mnit.erp.hr.controller;

import com.mnit.erp.hr.service.EmployeeStatusService;
import com.mnit.erp.hr.model.EmployeeStatus;

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
@RequestMapping("/employeestatus")
public class EmployeeStatusController {
    @Autowired
    EmployeeStatusService employeeStatusService;
    
    @GetMapping
    public CustomResponseMessage findAll(){
        List<EmployeeStatus> dataList = employeeStatusService.getAll();
        ResponseMessageType responseMessageType = Objects.nonNull(dataList) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(dataList) && dataList.size() > 0 ? "Success! Data is fetched." : "No record found.")
                .messageType(responseMessageType)
                .response(dataList).build();
    }
    
    @GetMapping("/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
        EmployeeStatus thisData = employeeStatusService.getById(id);
        ResponseMessageType responseMessageType = Objects.nonNull(thisData) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(thisData) ? "Success! Data is fetched." : "No record found.")
                .messageType(responseMessageType)
                .response(thisData).build();
    }
    
    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody EmployeeStatus createData){
        EmployeeStatus employeeStatus = null;
        try {
            employeeStatus = employeeStatusService.create(createData);
        } catch (Exception e) {
            ResponseMessageType responseMessageType = ResponseMessageType.ERROR;
            return CustomResponseMessage.builder().message("Error! "+e.getMessage())
                    .messageType(responseMessageType)
                    .response(null).build();
        }
        
        ResponseMessageType responseMessageType = Objects.nonNull(employeeStatus) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(employeeStatus) ? "Success! Data is Saved." : "Unable to add data!")
                .messageType(responseMessageType)
                .response(employeeStatus).build();
    }
    
    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody EmployeeStatus updateData){
        EmployeeStatus employeeStatus = null;
        try {
            employeeStatus = employeeStatusService.update(updateData);
        } catch (Exception e) {
            ResponseMessageType responseMessageType = ResponseMessageType.ERROR;
            return CustomResponseMessage.builder().message("Error! "+e.getMessage())
                    .messageType(responseMessageType)
                    .response(null).build();
        }
 
        ResponseMessageType responseMessageType = Objects.nonNull(employeeStatus) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(employeeStatus) ? "Success! Data is Saved." : "Unable to update data!")
                .messageType(responseMessageType)
                .response(employeeStatus).build();
    }
}
