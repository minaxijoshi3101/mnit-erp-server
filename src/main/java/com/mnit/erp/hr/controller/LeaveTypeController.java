package com.mnit.erp.hr.controller;

import com.mnit.erp.hr.service.LeaveTypeService;
import com.mnit.erp.hr.model.LeaveType;

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
@RequestMapping("/leavetype")
public class LeaveTypeController {
    @Autowired
    LeaveTypeService leaveTypeService;
    
    @GetMapping
    public CustomResponseMessage findAll(){
        List<LeaveType> dataList = leaveTypeService.getAll();
        ResponseMessageType responseMessageType = Objects.nonNull(dataList) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(dataList) && dataList.size() > 0 ? "Success! Data is fetched." : "No record found.")
                .messageType(responseMessageType)
                .response(dataList).build();
    }
    
    @GetMapping("/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
        LeaveType thisData = leaveTypeService.getById(id);
        ResponseMessageType responseMessageType = Objects.nonNull(thisData) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(thisData) ? "Success! Data is fetched." : "No record found.")
                .messageType(responseMessageType)
                .response(thisData).build();
    }
    
    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody LeaveType createData){
        LeaveType leavetype = null;
        try {
            leavetype = leaveTypeService.create(createData);
        } catch (Exception e) {
            ResponseMessageType responseMessageType = ResponseMessageType.ERROR;
            return CustomResponseMessage.builder().message("Error! "+e.getMessage())
                    .messageType(responseMessageType)
                    .response(null).build();
        }
        
        ResponseMessageType responseMessageType = Objects.nonNull(leavetype) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(leavetype) ? "Success! Data is Saved." : "Unable to add data!")
                .messageType(responseMessageType)
                .response(leavetype).build();
    }
    
    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody LeaveType updateData){
        LeaveType leavetype = null;
        try {
            leavetype = leaveTypeService.update(updateData);
        } catch (Exception e) {
            ResponseMessageType responseMessageType = ResponseMessageType.ERROR;
            return CustomResponseMessage.builder().message("Error! "+e.getMessage())
                    .messageType(responseMessageType)
                    .response(null).build();
        }
 
        ResponseMessageType responseMessageType = Objects.nonNull(leavetype) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(leavetype) ? "Success! Data is Saved." : "Unable to update data!")
                .messageType(responseMessageType)
                .response(leavetype).build();
    }
}
