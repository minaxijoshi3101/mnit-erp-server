package com.mnit.erp.academic.student.studentCategory.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.student.studentCategory.model.StudentCategory;
import com.mnit.erp.academic.student.studentCategory.service.StudentCategoryService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains student category rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 10 July, 2021
 */
@RestController
@RequestMapping("/student-category")
public class StudentCategoryController extends AbstractController {

    @Autowired
    StudentCategoryService studentCategoryService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody StudentCategory studentCategory){
        StudentCategory studentCategory1 = this.studentCategoryService.add(studentCategory);
        ResponseMessageType responseMessageType = Objects.nonNull(studentCategory1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentCategory1) ? "Student category added successfully!" : "Unable to add Student category!")
                .messageType(responseMessageType)
                .response(studentCategory1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody StudentCategory studentCategory){
        StudentCategory studentCategory1 = this.studentCategoryService.update(studentCategory);
        ResponseMessageType responseMessageType = Objects.nonNull(studentCategory1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentCategory1) ? "Student category updated successfully!" : "Unable to update Student category!")
                .messageType(responseMessageType)
                .response(studentCategory1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        StudentCategory studentCategory1 = this.studentCategoryService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(studentCategory1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentCategory1) ? "Student category found successfully!" : "Unable to find Student category!")
                .messageType(responseMessageType)
                .response(studentCategory1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<StudentCategory> studentCategories = this.studentCategoryService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(studentCategories) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentCategories) && !studentCategories.isEmpty() ? "Student categories found!" : "Unable to find Student categories!")
                .messageType(responseMessageType)
                .response(studentCategories).build();
    }

}
