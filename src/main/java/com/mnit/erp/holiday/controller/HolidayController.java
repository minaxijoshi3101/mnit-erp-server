package com.mnit.erp.holiday.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.holiday.model.Holiday;
import com.mnit.erp.holiday.service.HolidayService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/holiday")
public class HolidayController extends AbstractController {

    @Autowired
    HolidayService holidayService;

    @PostMapping("add")
    public CustomResponseMessage add(@RequestBody Holiday holiday){
        Holiday holiday1 = this.holidayService.add(holiday);
        ResponseMessageType responseMessageType = Objects.nonNull(holiday1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(holiday1) ? "Holiday added successfully in holiday calendar!" : "Unable to add holiday in calendar!")
                .messageType(responseMessageType)
                .response(holiday1).build();
    }

    @PutMapping("update")
    public CustomResponseMessage update(@RequestBody Holiday holiday){
        Holiday holiday1 = this.holidayService.update(holiday);
        ResponseMessageType responseMessageType = Objects.nonNull(holiday1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(holiday1) ? "Holiday updated successfully in holiday calendar!" : "Unable to update holiday in calendar!")
                .messageType(responseMessageType)
                .response(holiday1).build();
    }

    @GetMapping
    public Holiday find(Date date){
        return null;
    }

    @GetMapping("/findInYear/{year}")
    public CustomResponseMessage findAllInYear(@PathVariable Long year){
        List<Holiday> allInYear = this.holidayService.findAllInYear(year);
        ResponseMessageType responseMessageType = Objects.nonNull(allInYear) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(allInYear) && allInYear.size() > 0 ? "List of holidays in year loaded!" : "Unable to find holiday in given year!")
                .messageType(responseMessageType)
                .response(allInYear).build();
    }

    @GetMapping("/findAll")
    public CustomResponseMessage findAll(Pageable pageable){
        Page<Holiday> holidays = this.holidayService.findAll(pageable);
        ResponseMessageType responseMessageType = Objects.nonNull(holidays) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(holidays) && holidays.getSize() > 0 ? "List of holidays found!" : "Unable to find holidays!")
                .messageType(responseMessageType)
                .response(holidays).build();
    }

    @GetMapping("/findOnDate/{date}")
    public List<Holiday> findOnDate(@PathVariable Date date){
        return this.holidayService.findOnDate(date);
    }

}
