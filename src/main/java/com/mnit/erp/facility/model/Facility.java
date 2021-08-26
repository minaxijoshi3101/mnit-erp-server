package com.mnit.erp.facility.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Contains Facility model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of facility model
 * @date: 16 June, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Boolean status;
    public Facility(Long facilityId) {
        this.id = facilityId;
    }
}
