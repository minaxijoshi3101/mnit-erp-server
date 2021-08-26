package com.mnit.erp.academic.exam.attendance.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private Long studentId;
    private Boolean isPresent;
}
