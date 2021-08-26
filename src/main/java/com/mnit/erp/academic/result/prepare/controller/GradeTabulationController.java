package com.mnit.erp.academic.result.prepare.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.exam.gradeMark.model.GradeMark;
import com.mnit.erp.academic.exam.gradeMark.repository.GradeMarkRepository;
import com.mnit.erp.academic.result.prepare.model.CourseObject;
import com.mnit.erp.academic.result.prepare.service.GradeTabulationService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Contains Grade tabulation rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 06 July, 2021
 */
@RestController
@RequestMapping("/grade-tabulation")
public class GradeTabulationController extends AbstractController {

    @Autowired
    GradeTabulationService gradeTabulationService;

    @Autowired
    GradeMarkRepository gradeMarkRepository;

    @PostMapping("/generate")
    public CustomResponseMessage generate(@RequestBody Map<String, Object> requestObject){
        Long examId = Long.parseLong(requestObject.get("examId").toString());

        List<GradeMark> gradeMarkList = gradeMarkRepository.findByExam_Id(examId);
        List<CourseObject> courseObjectList = new ArrayList<>();
        Long semCreditsRegistered = 0L;
        Long semCreditsEarned = 0L;
        Long semGradePoints = 0L;
        Long prevCreditsRegistered = 0L;
        Long prevCreditsEarned = 0L;
        Long prevGradePoints = 0L;
        Long totalCreditsRegistered = 0L;
        Long totalCreditsEarned = 0L;
        Long totalGradePoints = 0L;
        Double sgpa = 0.0;
        Double cgpa = 0.0;

        for (GradeMark gradeMark: gradeMarkList){
            CourseObject courseObject = new CourseObject();

            courseObject.setCourseName(gradeMark.getCourse().getName());
            courseObject.setCourseCode(gradeMark.getCourse().getAbbreviation());
            courseObject.setCredits(gradeMark.getCourse().getCredits());
            courseObject.setGrade(gradeMark.getGrade());
            courseObject.setGradePoints(gradeMark.getGradePoints());
            courseObject.setExamType(gradeMark.getExam().getExamType());
            courseObjectList.add(courseObject);

            semCreditsRegistered +=gradeMark.getCourse().getCredits();
            semCreditsEarned +=(gradeMark.getGradePoints()>0)? gradeMark.getCourse().getCredits() : 0;
            semGradePoints +=(gradeMark.getGradePoints()>0)? gradeMark.getGradePoints() : 0;


        }

        ResponseMessageType responseMessageType = 1==1 ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(1==1 ? "Grade tabulation added successfully!" : "Unable to add tabulation mark!")
                .messageType(responseMessageType)
                .response(courseObjectList).build();
    }
}
