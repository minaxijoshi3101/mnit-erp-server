package com.mnit.erp.country.model;

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
public class Country {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    String name;
    Boolean active;

    public Country(Long countryId) {
        this.id = countryId;
    }
}
