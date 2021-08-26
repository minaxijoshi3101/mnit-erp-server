package com.mnit.erp.academic.semester.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=Semester.class)
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long semester;
    String name;
    String type;
    Boolean status;

    public Semester(Long semesterId) {
        this.id = semesterId;
    }

}
