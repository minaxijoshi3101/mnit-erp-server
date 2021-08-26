package com.mnit.erp.holiday.service;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.holiday.model.Holiday;
import com.mnit.erp.holiday.repository.HolidayRepository;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class HolidayService {

    @Autowired
    HolidayRepository holidayRepository;


    public Holiday add(Holiday holiday) {
        if(this.validate(holiday)){
            return this.holidayRepository.save(holiday);
        }
        return null;
    }

    public Holiday update(Holiday holiday) {
        Holiday holiday1 = this.holidayRepository.findById(holiday.getId()).orElse(null);
        if(Objects.isNull(holiday1)){
            throw new ServiceException("Holiday record not found. Can't update!");
        }
        if(this.validate(holiday)){
            CommonUtils.copyNonNullProperties(holiday, holiday1);
            return this.holidayRepository.save(holiday);
        }
        return null;
    }

    private boolean validate(Holiday holiday){
        return true;
    }

    public Page<Holiday> findAll(Pageable pageable) {
        return this.holidayRepository.findAll(pageable);
    }

    public List<Holiday> findAllInYear(Long year) {
        return this.holidayRepository.findAllInYear(year);
    }

    public List<Holiday> findOnDate(Date date) {
        return this.holidayRepository.findAllOnDate(date);
    }

}
