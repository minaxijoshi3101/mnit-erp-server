package com.mnit.erp.department.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=Department.class)
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String type;
    Boolean active;
    public Department(Long departmentId) {
        this.id = departmentId;
    }
}
