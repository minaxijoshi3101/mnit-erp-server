package com.mnit.erp.academic.specialization.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnit.erp.academic.branch.model.Branch;
import com.mnit.erp.academic.program.model.Program;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Specialization {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    private String abbreviation;
    private String name;
    private Boolean active;
    private String code; // TO BE USED IN STUDENT ID GENERATION

    @ManyToOne
    private Branch branch;

    public Specialization(Long specializationId) {
        this.id = specializationId;
    }
}
