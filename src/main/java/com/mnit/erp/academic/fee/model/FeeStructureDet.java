package com.mnit.erp.academic.fee.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnit.erp.user.model.User;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FeeStructureDet implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JsonIgnore
    private FeeStructure feeStructure; 
    
    @ManyToOne
    @JsonIgnore
    private FeeHead feeHead;

    @ManyToOne
    @JsonIgnore
    private FeeSubHead feeSubHead;
    
    //Amount
    private Long feeAmount;
    @CreationTimestamp
    private Date createdOn;
    private String status;
    
    @Transient
    private Long feeHeadId;
    public void setFeeHeadId(Long feeHeadId) {
    	this.feeHead = new FeeHead();
    	this.feeHead.setId(feeHeadId);
    }
    public Long getFeeHeadId() {
    	return feeHead.getId();
    }
    
    @Transient
    private Long feeSubHeadId;
    public void setFeeSubHeadId(Long feeSubHeadId) {
    	this.feeSubHead = new FeeSubHead();
    	this.feeSubHead.setId(feeSubHeadId);
    }
    public Long getFeeSubHeadId() {
    	return feeSubHead.getId();
    }

    @Transient
    private String feeHeadName;
    public String getFeeHeadName() {
    	return feeHead.getName();
    }

    @Transient
    private String feeSubHeadName;
    public String getFeeSubHeadName() {
    	return feeSubHead.getName();
    }
    
    public void updateJson(FeeStructureDet feeStructureDet) {
        if (feeStructureDet != null) {
        	this.feeStructure = (FeeStructure) (feeStructureDet.getFeeStructure() == null? this.feeStructure : feeStructureDet.getFeeStructure());
        	this.feeHead = (FeeHead) (feeStructureDet.getFeeHead() == null? this.feeHead : feeStructureDet.getFeeHead());
        	this.feeSubHead = (FeeSubHead) (feeStructureDet.getFeeSubHead() == null? this.feeSubHead : feeStructureDet.getFeeSubHead());
        	this.feeAmount = (Long) (feeStructureDet.getFeeAmount() == null? this.feeAmount : feeStructureDet.getFeeAmount());
        	this.status = (String) (feeStructureDet.getStatus() == null? this.status : feeStructureDet.getStatus());
        }
    }  
}
