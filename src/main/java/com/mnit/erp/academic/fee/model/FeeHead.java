package com.mnit.erp.academic.fee.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class FeeHead implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String abbreviation;
    private String name;
    private Long seqNo;
    private Boolean status;
    
    public void updateJson(FeeHead feeHead) {
        if (feeHead != null) {
        	this.abbreviation = feeHead.getAbbreviation() == null? this.abbreviation : feeHead.getAbbreviation();
        	this.name = feeHead.getName() == null? this.name: feeHead.getName();
        	this.seqNo = feeHead.getSeqNo();
        	this.status = feeHead.getStatus() == null? this.status : feeHead.getStatus();
        }
    }

}
