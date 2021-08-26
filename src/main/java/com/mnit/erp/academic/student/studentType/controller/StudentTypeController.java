package com.mnit.erp.academic.student.studentType.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.student.studentType.model.StudentType;
import com.mnit.erp.academic.student.studentType.service.StudentTypeService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains student type rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 09 July, 2021
 */
@RestController
@RequestMapping("/student-type")
public class StudentTypeController extends AbstractController {

    @Autowired
    StudentTypeService studentTypeService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody StudentType studentType){
        StudentType studentType1 = this.studentTypeService.add(studentType);
        ResponseMessageType responseMessageType = Objects.nonNull(studentType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentType1) ? "Student type added successfully!" : "Unable to add Student type!")
                .messageType(responseMessageType)
                .response(studentType1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody StudentType studentType){
        StudentType studentType1 = this.studentTypeService.update(studentType);
        ResponseMessageType responseMessageType = Objects.nonNull(studentType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentType1) ? "Student type updated successfully!" : "Unable to update Student type!")
                .messageType(responseMessageType)
                .response(studentType1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        StudentType studentType1 = this.studentTypeService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(studentType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentType1) ? "Student type found successfully!" : "Unable to find Student type!")
                .messageType(responseMessageType)
                .response(studentType1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<StudentType> studentTypes = this.studentTypeService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(studentTypes) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentTypes) && !studentTypes.isEmpty() ? "Student type found!" : "Unable to find Student types!")
                .messageType(responseMessageType)
                .response(studentTypes).build();
    }

}
