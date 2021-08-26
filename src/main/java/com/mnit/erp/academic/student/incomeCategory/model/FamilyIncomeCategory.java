package com.mnit.erp.academic.student.incomeCategory.model;

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
public class FamilyIncomeCategory {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String description;
    Double minIncome;
    Double maxIncome;
    Boolean status;

    public FamilyIncomeCategory(Long familyIncomeCategoryId) {
        this.id = familyIncomeCategoryId;
    }
}
