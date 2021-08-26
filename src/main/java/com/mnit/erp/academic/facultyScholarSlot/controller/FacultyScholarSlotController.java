package com.mnit.erp.academic.facultyScholarSlot.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.facultyScholarSlot.model.FacultyScholarSlot;
import com.mnit.erp.academic.facultyScholarSlot.model.SlotApproveStatus;
import com.mnit.erp.academic.facultyScholarSlot.service.FacultyScholarSlotService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains faculty scholar slot rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 27 July, 2021
 */
@RestController
@RequestMapping("/faculty-scholar-slot")
public class FacultyScholarSlotController extends AbstractController {

    @Autowired
    FacultyScholarSlotService facultyScholarSlotService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody FacultyScholarSlot facultyScholarSlot){
        FacultyScholarSlot facultyScholarSlot1 = this.facultyScholarSlotService.add(facultyScholarSlot);
        ResponseMessageType responseMessageType = Objects.nonNull(facultyScholarSlot1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(facultyScholarSlot1) ? "faculty scholar slot added successfully!" : "Unable to add faculty scholar slot!")
                .messageType(responseMessageType)
                .response(facultyScholarSlot1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody FacultyScholarSlot facultyScholarSlot){
        FacultyScholarSlot facultyScholarSlot1 = this.facultyScholarSlotService.update(facultyScholarSlot);
        ResponseMessageType responseMessageType = Objects.nonNull(facultyScholarSlot1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(facultyScholarSlot1) ? "faculty scholar slot updated successfully!" : "Unable to update faculty scholar slot!")
                .messageType(responseMessageType)
                .response(facultyScholarSlot1).build();
    }

    @PutMapping("/approveOrReject")
    public CustomResponseMessage approveOrReject(@RequestBody FacultyScholarSlot facultyScholarSlot){
        String action="";
        String action_past_participle;
        if(facultyScholarSlot.getSlotApproveStatus() == SlotApproveStatus.APPROVED){
            action = "approve";
            action_past_participle = "approved";
        }else if(facultyScholarSlot.getSlotApproveStatus() == SlotApproveStatus.REJECTED){
            action = "reject";
            action_past_participle = "rejected";
        }else{
            return CustomResponseMessage.builder().message("Bad request")
                    .messageType(ResponseMessageType.ERROR)
                    .response(null).build();
        }
        FacultyScholarSlot facultyScholarSlot1 = this.facultyScholarSlotService.approveOrReject(facultyScholarSlot);
        ResponseMessageType responseMessageType = Objects.nonNull(facultyScholarSlot1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(facultyScholarSlot1) ? "faculty scholar slot "+action_past_participle+" successfully!" : "Unable to "+action+" faculty scholar slot!")
                .messageType(responseMessageType)
                .response(facultyScholarSlot1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        FacultyScholarSlot facultyScholarSlot1 = this.facultyScholarSlotService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(facultyScholarSlot1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(facultyScholarSlot1) ? "faculty scholar slot found successfully!" : "Unable to find faculty scholar slot!")
                .messageType(responseMessageType)
                .response(facultyScholarSlot1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<FacultyScholarSlot> facultyScholarSlots = this.facultyScholarSlotService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(facultyScholarSlots) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(facultyScholarSlots) && !facultyScholarSlots.isEmpty() ? "faculty scholar slot found!" : "Unable to find faculty scholar slots!")
                .messageType(responseMessageType)
                .response(facultyScholarSlots).build();
    }

}
