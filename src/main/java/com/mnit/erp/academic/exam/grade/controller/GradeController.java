package com.mnit.erp.academic.exam.grade.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.exam.grade.model.Grade;
import com.mnit.erp.academic.exam.grade.service.GradeService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains Grade rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 29 June, 2021
 */
@RestController
@RequestMapping("/grade")
public class GradeController extends AbstractController {

    @Autowired
    GradeService gradeService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody Grade grade){
        Grade grade1 = this.gradeService.add(grade);
        ResponseMessageType responseMessageType = Objects.nonNull(grade1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(grade1) ? "Grade added successfully!" : "Unable to add grade!")
                .messageType(responseMessageType)
                .response(grade1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody Grade grade){
        Grade grade1 = this.gradeService.update(grade);
        ResponseMessageType responseMessageType = Objects.nonNull(grade1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(grade1) ? "Grade updated successfully!" : "Unable to update grade!")
                .messageType(responseMessageType)
                .response(grade1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Grade grade1 = this.gradeService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(grade1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(grade1) ? "Grade found successfully!" : "Unable to find grade!")
                .messageType(responseMessageType)
                .response(grade1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Grade> grades = this.gradeService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(grades) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(grades) && !grades.isEmpty() ? "Grades found!" : "Unable to find grades!")
                .messageType(responseMessageType)
                .response(grades).build();
    }

}
