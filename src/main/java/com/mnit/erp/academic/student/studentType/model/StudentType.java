package com.mnit.erp.academic.student.studentType.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Contains student type model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of student type model
 * @date: 09 July, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String name;
    @Column(nullable = false)
    Boolean status;

    public StudentType(Long studentTypeId) {
        this.id = studentTypeId;
    }
}
