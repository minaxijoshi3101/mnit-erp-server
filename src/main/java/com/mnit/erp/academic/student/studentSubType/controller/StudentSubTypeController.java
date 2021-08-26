package com.mnit.erp.academic.student.studentSubType.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.student.studentSubType.model.StudentSubType;
import com.mnit.erp.academic.student.studentSubType.service.StudentSubTypeService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains student sub type rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 10 July, 2021
 */
@RestController
@RequestMapping("/student-sub-type")
public class StudentSubTypeController extends AbstractController {

    @Autowired
    StudentSubTypeService studentSubTypeService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody StudentSubType studentSubType){
        StudentSubType studentSubType1 = this.studentSubTypeService.add(studentSubType);
        ResponseMessageType responseMessageType = Objects.nonNull(studentSubType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentSubType1) ? "Student sub type added successfully!" : "Unable to add Student sub type!")
                .messageType(responseMessageType)
                .response(studentSubType1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody StudentSubType studentSubType){
        StudentSubType studentSubType1 = this.studentSubTypeService.update(studentSubType);
        ResponseMessageType responseMessageType = Objects.nonNull(studentSubType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentSubType1) ? "Student sub type updated successfully!" : "Unable to update Student sub type!")
                .messageType(responseMessageType)
                .response(studentSubType1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        StudentSubType studentSubType1 = this.studentSubTypeService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(studentSubType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentSubType1) ? "Student sub type found successfully!" : "Unable to find Student sub type!")
                .messageType(responseMessageType)
                .response(studentSubType1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<StudentSubType> studentSubTypes = this.studentSubTypeService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(studentSubTypes) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentSubTypes) && !studentSubTypes.isEmpty() ? "Student sub types found!" : "Unable to find Student sub types!")
                .messageType(responseMessageType)
                .response(studentSubTypes).build();
    }

}
