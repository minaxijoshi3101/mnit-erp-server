package com.mnit.erp.content.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Module {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String description;
    Boolean active;

    public Module(Long moduleId) {
        this.id = moduleId;
    }
}
