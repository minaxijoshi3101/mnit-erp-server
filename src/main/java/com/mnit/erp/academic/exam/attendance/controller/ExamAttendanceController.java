package com.mnit.erp.academic.exam.attendance.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.exam.attendance.model.ExamAttendance;
import com.mnit.erp.academic.exam.attendance.service.ExamAttendanceService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains Exam attendance rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 24 June, 2021
 */
@RestController
@RequestMapping("/exam-attendance")
public class ExamAttendanceController extends AbstractController {

    @Autowired
    ExamAttendanceService examAttendanceService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody ExamAttendance examAttendance){
        ExamAttendance examAttendance1 = this.examAttendanceService.add(examAttendance);
        ResponseMessageType responseMessageType = Objects.nonNull(examAttendance1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(examAttendance1) ? "Exam attendance added successfully!" : "Unable to add exam attendance!")
                .messageType(responseMessageType)
                .response(examAttendance1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody ExamAttendance examAttendance){
        ExamAttendance examAttendance1 = this.examAttendanceService.update(examAttendance);
        ResponseMessageType responseMessageType = Objects.nonNull(examAttendance1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(examAttendance1) ? "Exam attendance updated successfully!" : "Unable to update exam attendance!")
                .messageType(responseMessageType)
                .response(examAttendance1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        ExamAttendance examAttendance1 = this.examAttendanceService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(examAttendance1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(examAttendance1) ? "Exam attendance found successfully!" : "Unable to find exam attendance!")
                .messageType(responseMessageType)
                .response(examAttendance1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<ExamAttendance> examAttendances = this.examAttendanceService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(examAttendances) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(examAttendances) && !examAttendances.isEmpty() ? "Exam attendances found!" : "Unable to find exam attendances!")
                .messageType(responseMessageType)
                .response(examAttendances).build();
    }

}
