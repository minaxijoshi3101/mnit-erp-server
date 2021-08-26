package com.mnit.erp.academic.selectionboard.type.model;

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
public class SelectionBoardType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String abbreviation;
    String name;
    Boolean status;

    public SelectionBoardType(Long id) {
        this.id = id;
    }
}
