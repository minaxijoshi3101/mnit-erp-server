package com.mnit.erp.academic.preExamAttendance.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.preExamAttendance.model.PreExamAttendance;
import com.mnit.erp.academic.preExamAttendance.model.Student;
import com.mnit.erp.academic.preExamAttendance.service.PreExamAttendanceService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
/**
 * Contains PreExamAttendance rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 11 June, 2021
 */
@RestController
@RequestMapping("/pre-exam-attendance")
public class PreExamAttendanceController extends AbstractController {

    @Autowired
    PreExamAttendanceService preExamAttendanceService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody PreExamAttendance preexamattendance){
        PreExamAttendance preExamAttendance1 = this.preExamAttendanceService.add(preexamattendance);
        ResponseMessageType responseMessageType = Objects.nonNull(preExamAttendance1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(preExamAttendance1) ? "Pre exam attendance added successfully!" : "Unable to add pre exam attendance!")
                .messageType(responseMessageType)
                .response(preExamAttendance1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody PreExamAttendance preexamattendance){
        PreExamAttendance preExamAttendance1 = this.preExamAttendanceService.update(preexamattendance);
        ResponseMessageType responseMessageType = Objects.nonNull(preExamAttendance1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(preExamAttendance1) ? "Pre exam attendance updated successfully!" : "Unable to update pre exam attendance!")
                .messageType(responseMessageType)
                .response(preExamAttendance1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        PreExamAttendance preExamAttendance1 = this.preExamAttendanceService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(preExamAttendance1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(preExamAttendance1) ? "PreExamAttendance found successfully!" : "Unable to find pre exam attendance!")
                .messageType(responseMessageType)
                .response(preExamAttendance1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<PreExamAttendance> preExamAttendances = this.preExamAttendanceService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(preExamAttendances) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(preExamAttendances) && !preExamAttendances.isEmpty() ? "Pre exam attendances found successfully!" : "Unable to find pre exam attendances!")
                .messageType(responseMessageType)
                .response(preExamAttendances).build();
    }

    @ApiOperation(httpMethod = "GET", value = "The API returns all the eligible students for a given exam, course and section")
    @GetMapping("/geteligiblestudentsforexam")
    public CustomResponseMessage findEligibleStudents(@RequestParam boolean isFa, @RequestParam long examId,
                                                      @RequestParam long courseId, @RequestParam long sectionId){
        List<Student> studentList = this.preExamAttendanceService.findEligibleStudents(isFa, examId, courseId, sectionId);
        ResponseMessageType responseMessageType = Objects.nonNull(studentList) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentList) ? "Eligible Students for exam found successfully!" : "Unable to find eligible students for exam!")
                .messageType(responseMessageType)
                .response(studentList).build();
    }
}
