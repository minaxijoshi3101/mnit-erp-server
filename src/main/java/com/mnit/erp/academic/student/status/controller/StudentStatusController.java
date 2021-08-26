package com.mnit.erp.academic.student.status.controller;

import com.mnit.erp.academic.student.status.model.StudentStatus;
import com.mnit.erp.academic.student.status.service.StudentStatusService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/studentStatus")
public class StudentStatusController {

    @Autowired
    StudentStatusService studentStatusService;

    @PostMapping
    public CustomResponseMessage add(@RequestBody StudentStatus studentStatus){
        StudentStatus status = this.studentStatusService.add(studentStatus);
        ResponseMessageType responseMessageType = Objects.nonNull(status) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(status) ? "Student status (Master) added successfully! !" : "Unable to add student status in masters!")
                .messageType(responseMessageType)
                .response(status).build();
    }

    @PutMapping
    public CustomResponseMessage update(@RequestBody StudentStatus studentStatus){
        StudentStatus status = this.studentStatusService.update(studentStatus);
        ResponseMessageType responseMessageType = Objects.nonNull(status) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(status) ? "Student status (Master) updated successfully! !" : "Unable to update student status in masters!")
                .messageType(responseMessageType)
                .response(status).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        StudentStatus status = this.studentStatusService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(status) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(status) ? "Student status found successfully! !" : "Unable to find student status in masters!")
                .messageType(responseMessageType)
                .response(status).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<StudentStatus> statuses = this.studentStatusService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(statuses) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(statuses) ? "Student status list loaded successfully! !" : "Unable to load student status from masters!")
                .messageType(responseMessageType)
                .response(statuses).build();
    }

}
