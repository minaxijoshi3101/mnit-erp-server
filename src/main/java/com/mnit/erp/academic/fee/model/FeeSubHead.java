package com.mnit.erp.academic.fee.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class FeeSubHead implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private FeeHead feehead;

    private String abbreviation;
    private String name;
    private int seqNo;
    private Boolean status;
    
    @Transient
    private Long feeHeadId;
    public void setFeeHeadId(Long feeHeadId) {
    	this.feehead = new FeeHead();
    	this.feehead.setId(feeHeadId);
    }
    public Long getFeeHeadId() {
    	return feehead.getId();
    }

    public void updateJson(FeeSubHead feeSubHead) {
        if (feeSubHead != null) {
        	this.abbreviation = feeSubHead.getAbbreviation() == null? this.abbreviation : feeSubHead.getAbbreviation();
        	this.name = feeSubHead.getName() == null? this.name: feeSubHead.getName();
        	this.seqNo = feeSubHead.getSeqNo();
        	this.status = feeSubHead.getStatus() == null? this.status : feeSubHead.getStatus();
        }
    }
}
