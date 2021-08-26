package com.mnit.erp.academic.exam.calendar.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.exam.calendar.model.ExamCalendar;
import com.mnit.erp.academic.exam.calendar.service.ExamCalendarService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains Exam calendar rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 22 June, 2021
 */
@RestController
@RequestMapping("/exam-calendar")
public class ExamCalendarController extends AbstractController {

    @Autowired
    ExamCalendarService examCalendarService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody ExamCalendar examCalendar){
        ExamCalendar examCalendar1 = this.examCalendarService.add(examCalendar);
        ResponseMessageType responseMessageType = Objects.nonNull(examCalendar1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(examCalendar1) ? "Exam calendar added successfully in masters!" : "Unable to add exam calendar in masters!")
                .messageType(responseMessageType)
                .response(examCalendar1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody ExamCalendar examCalendar){
        ExamCalendar examCalendar1 = this.examCalendarService.update(examCalendar);
        ResponseMessageType responseMessageType = Objects.nonNull(examCalendar1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(examCalendar1) ? "Exam calendar updated successfully in masters!" : "Unable to update exam calendar in masters!")
                .messageType(responseMessageType)
                .response(examCalendar1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        ExamCalendar examCalendar1 = this.examCalendarService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(examCalendar1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(examCalendar1) ? "Exam calendar found successfully in masters!" : "Unable to find exam calendar in masters!")
                .messageType(responseMessageType)
                .response(examCalendar1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<ExamCalendar> examCalendars = this.examCalendarService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(examCalendars) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(examCalendars) && !examCalendars.isEmpty() ? "Exam calendars found successfully in masters!" : "Unable to find exam calendars in masters!")
                .messageType(responseMessageType)
                .response(examCalendars).build();
    }

}
