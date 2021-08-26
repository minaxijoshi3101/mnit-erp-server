package com.mnit.erp.academic.semester.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.semester.model.Semester;
import com.mnit.erp.academic.semester.service.SemesterService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/semester")
public class SemesterController extends AbstractController {

    @Autowired
    SemesterService semesterService;

    @GetMapping("/findAll")
    public CustomResponseMessage findAll(){
        List<Semester> semesters = this.semesterService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(semesters) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(semesters) && semesters.size() > 0 ? "Semesters found in masters!" : "Unable to find semesters in masters!")
                .messageType(responseMessageType)
                .response(semesters).build();
    }

    @GetMapping("/findSemester/{semester}")
    public CustomResponseMessage findSemester(@PathVariable Long semester){
        Semester semester1 = this.semesterService.findSemester(semester);
        ResponseMessageType responseMessageType = Objects.nonNull(semester1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(semester1) ? "Semester found in masters!" : "Unable to find semester in masters!")
                .messageType(responseMessageType)
                .response(semester1).build();
    }

    @GetMapping("/findByType/{type}")
    public CustomResponseMessage findAllByType(@PathVariable String type){
        List<Semester> semesters = this.semesterService.findAllByType(type);
        ResponseMessageType responseMessageType = Objects.nonNull(semesters) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(semesters) && semesters.size() > 0 ? "Semesters found in masters!" : "Unable to find semesters in masters!")
                .messageType(responseMessageType)
                .response(semesters).build();
    }

}
