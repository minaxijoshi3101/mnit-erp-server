package com.mnit.erp.academic.preExamAttendance.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private Long studentId;
    private Boolean isFa;
    private String name;
}
