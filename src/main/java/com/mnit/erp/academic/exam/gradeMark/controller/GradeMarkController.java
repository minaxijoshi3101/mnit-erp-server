package com.mnit.erp.academic.exam.gradeMark.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.exam.gradeMark.model.GradeMark;
import com.mnit.erp.academic.exam.gradeMark.service.GradeMarkService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains Grade Mark rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 29 June, 2021
 */
@RestController
@RequestMapping("/grade-mark")
public class GradeMarkController extends AbstractController {

    @Autowired
    GradeMarkService gradeMarkService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody GradeMark gradeMark){
        GradeMark gradeMark1 = this.gradeMarkService.add(gradeMark);
        ResponseMessageType responseMessageType = Objects.nonNull(gradeMark1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(gradeMark1) ? "Grade mark added successfully!" : "Unable to add grade mark!")
                .messageType(responseMessageType)
                .response(gradeMark1).build();
    }
    @PostMapping("/multiple")
    public CustomResponseMessage saveAll(@RequestBody List<GradeMark> gradeMarks){
        List<GradeMark> gradeMarks1 = this.gradeMarkService.saveAll(gradeMarks);
        ResponseMessageType responseMessageType = Objects.nonNull(gradeMarks1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(gradeMarks1) ? "Grade marks added successfully!" : "Unable to add grade marks!")
                .messageType(responseMessageType)
                .response(gradeMarks1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody GradeMark gradeMark){
        GradeMark gradeMark1 = this.gradeMarkService.update(gradeMark);
        ResponseMessageType responseMessageType = Objects.nonNull(gradeMark1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(gradeMark1) ? "Grade mark updated successfully!" : "Unable to update grade mark!")
                .messageType(responseMessageType)
                .response(gradeMark1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        GradeMark gradeMark1 = this.gradeMarkService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(gradeMark1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(gradeMark1) ? "Grade mark found successfully!" : "Unable to find grade mark!")
                .messageType(responseMessageType)
                .response(gradeMark1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<GradeMark> gradeMarks = this.gradeMarkService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(gradeMarks) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(gradeMarks) && !gradeMarks.isEmpty() ? "Grades marks found!" : "Unable to find grade marks!")
                .messageType(responseMessageType)
                .response(gradeMarks).build();
    }

}
