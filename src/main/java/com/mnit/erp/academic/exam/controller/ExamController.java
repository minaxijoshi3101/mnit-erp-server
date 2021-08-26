package com.mnit.erp.academic.exam.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.exam.model.Exam;
import com.mnit.erp.academic.exam.service.ExamService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
/**
 * Contains Exam rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 08 June, 2021
 */
@RestController
@RequestMapping("/exam")
public class ExamController extends AbstractController {

    @Autowired
    ExamService examService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody Exam exam){
        Exam exam1 = this.examService.add(exam);
        ResponseMessageType responseMessageType = Objects.nonNull(exam1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(exam1) ? "Exam added successfully in masters!" : "Unable to add exam in masters!")
                .messageType(responseMessageType)
                .response(exam1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody Exam exam){
        Exam exam1 = this.examService.update(exam);
        ResponseMessageType responseMessageType = Objects.nonNull(exam1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(exam1) ? "Exam updated successfully in masters!" : "Unable to update exam in masters!")
                .messageType(responseMessageType)
                .response(exam1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Exam exam1 = this.examService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(exam1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(exam1) ? "Exam found successfully in masters!" : "Unable to find exam in masters!")
                .messageType(responseMessageType)
                .response(exam1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Exam> exams = this.examService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(exams) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(exams) && !exams.isEmpty() ? "Exams found successfully in masters!" : "Unable to find exams in masters!")
                .messageType(responseMessageType)
                .response(exams).build();
    }

}
