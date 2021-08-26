package com.mnit.erp.holiday.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity
public class Holiday {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    Date holidayDate;

    String description;
    HolidayType type;
    Boolean status;
}
