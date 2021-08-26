package com.mnit.erp.academic.section.model;

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
public class Section {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Boolean status;

    public Section(Long sectionId) {
        this.id = sectionId;
    }
}
