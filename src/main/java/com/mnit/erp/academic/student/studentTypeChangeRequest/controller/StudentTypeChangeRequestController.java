package com.mnit.erp.academic.student.studentTypeChangeRequest.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.student.studentTypeChangeRequest.model.RequestApproveStatus;
import com.mnit.erp.academic.student.studentTypeChangeRequest.model.StudentTypeChangeRequest;
import com.mnit.erp.academic.student.studentTypeChangeRequest.service.StudentTypeChangeRequestService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains student type change request rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 22 July, 2021
 */
@RestController
@RequestMapping("/student-type-change-request")
public class StudentTypeChangeRequestController extends AbstractController {

    @Autowired
    StudentTypeChangeRequestService studentTypeChangeRequestService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody StudentTypeChangeRequest studentTypeChangeRequest){
        StudentTypeChangeRequest studentTypeChangeRequest1 = this.studentTypeChangeRequestService.add(studentTypeChangeRequest);
        ResponseMessageType responseMessageType = Objects.nonNull(studentTypeChangeRequest1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentTypeChangeRequest1) ? "Student type change request added successfully!" : "Unable to add student type change request!")
                .messageType(responseMessageType)
                .response(studentTypeChangeRequest1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody StudentTypeChangeRequest studentTypeChangeRequest){
        StudentTypeChangeRequest studentTypeChangeRequest1 = this.studentTypeChangeRequestService.update(studentTypeChangeRequest);
        ResponseMessageType responseMessageType = Objects.nonNull(studentTypeChangeRequest1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentTypeChangeRequest1) ? "Student type change request updated successfully!" : "Unable to update student type change request!")
                .messageType(responseMessageType)
                .response(studentTypeChangeRequest1).build();
    }

    @PutMapping("/approveOrReject")
    public CustomResponseMessage approveOrReject(@RequestBody StudentTypeChangeRequest studentTypeChangeRequest){
        String action="";
        String action_past_participle;
        if(studentTypeChangeRequest.getStatus() == RequestApproveStatus.APPROVED){
            action = "approve";
            action_past_participle = "approved";
        }else if(studentTypeChangeRequest.getStatus() == RequestApproveStatus.REJECTED){
            action = "reject";
            action_past_participle = "rejected";
        }else{
            return CustomResponseMessage.builder().message("Bad request")
                    .messageType(ResponseMessageType.ERROR)
                    .response(null).build();
        }
        StudentTypeChangeRequest studentTypeChangeRequest1 = this.studentTypeChangeRequestService.approveOrReject(studentTypeChangeRequest);
        ResponseMessageType responseMessageType = Objects.nonNull(studentTypeChangeRequest1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentTypeChangeRequest1) ? "Student type change request "+action_past_participle+" successfully!" : "Unable to "+action+" student type change request!")
                .messageType(responseMessageType)
                .response(studentTypeChangeRequest1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        StudentTypeChangeRequest studentTypeChangeRequest1 = this.studentTypeChangeRequestService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(studentTypeChangeRequest1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentTypeChangeRequest1) ? "Student type change request found successfully!" : "Unable to find student type change request!")
                .messageType(responseMessageType)
                .response(studentTypeChangeRequest1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<StudentTypeChangeRequest> studentTypeChangeRequests = this.studentTypeChangeRequestService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(studentTypeChangeRequests) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentTypeChangeRequests) && !studentTypeChangeRequests.isEmpty() ? "Student type change request found!" : "Unable to find student type change requests!")
                .messageType(responseMessageType)
                .response(studentTypeChangeRequests).build();
    }

}
