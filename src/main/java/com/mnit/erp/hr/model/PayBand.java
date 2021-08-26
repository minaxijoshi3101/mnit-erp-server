package com.mnit.erp.hr.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Prahalad
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class PayBand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique=true)
    String name;
    
    Boolean status;

    public PayBand(Long id) {
        this.id = id;
    }
}
