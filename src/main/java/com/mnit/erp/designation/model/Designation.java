package com.mnit.erp.designation.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String abbreviation;
    String name;

    public Designation(Long id) {
        this.id = id;
    }

        
}
