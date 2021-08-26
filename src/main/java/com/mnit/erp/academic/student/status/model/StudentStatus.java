package com.mnit.erp.academic.student.status.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudentStatus {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String abbreviation;
    String name;
    String description;
    Boolean active;
    Boolean admissionStatus;
}
