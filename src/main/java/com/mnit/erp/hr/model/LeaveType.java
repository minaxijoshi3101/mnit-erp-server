package com.mnit.erp.hr.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class LeaveType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique=true)
    String name;
    
    @Column(unique=true)
    String abbreviation;
    
    Boolean status;    

    public LeaveType(Long id) {
        this.id = id;
    }
    
    
}
