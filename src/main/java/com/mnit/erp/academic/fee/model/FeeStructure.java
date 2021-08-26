package com.mnit.erp.academic.fee.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnit.erp.academic.student.incomeCategory.model.FamilyIncomeCategory;
import com.mnit.erp.user.model.User;

@Entity
@Data
public class FeeStructure implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String feeMasterType;
    private String acYear;
    private String semester;
    private String studentType;
    private String degree;
    private String admissionType;
    private String category;

    @ManyToOne
    @JsonIgnore
    private FamilyIncomeCategory familyIncomeCategory;

    private String currency;
    private String status;

    @ManyToOne
    @JsonIgnore
    private User user;
    @CreationTimestamp
    private Date createdOn;

    @Transient
    private List<FeeStructureDet> feeStructureDet;

    @Transient
    private Long familyIncomeCategoryId;
    public void setFamilyIncomeCategoryId(Long familyIncomeCategoryId) {
    	this.familyIncomeCategory = new FamilyIncomeCategory();
    	this.familyIncomeCategory.setId(familyIncomeCategoryId);
    }
    public Long getFamilyIncomeCategoryId() {
    	return familyIncomeCategory.getId();
    }
    
    @Transient
    private Long userId;
    public void setUserId(Long userId) {
    	if(userId != null){
        	this.user = new User();
        	this.user.setId(userId);
    	}
    }
    public Long getUserId() {
    	return user.getId();
    }
    
    
    public void updateJson(FeeStructure feeStructure) {
        if (feeStructure != null) {
        	this.feeMasterType = (String) (feeStructure.getFeeMasterType() == null? this.feeMasterType : feeStructure.getFeeMasterType());
        	this.semester = (String) (feeStructure.getSemester() == null? this.semester : feeStructure.getSemester());
        	this.studentType = (String) (feeStructure.getStudentType() == null? this.studentType : feeStructure.getStudentType());
        	this.degree = (String) (feeStructure.getDegree() == null? this.degree : feeStructure.getDegree());
        	this.admissionType = (String) (feeStructure.getAdmissionType() == null? this.admissionType : feeStructure.getAdmissionType());
        	this.category = (String) (feeStructure.getCategory() == null? this.category : feeStructure.getCategory());
        	this.familyIncomeCategory = (FamilyIncomeCategory) (feeStructure.getFamilyIncomeCategory() == null? this.familyIncomeCategory : feeStructure.getFamilyIncomeCategory());
        	this.currency = (String) (feeStructure.getCurrency() == null? this.currency : feeStructure.getCurrency());
        	this.user = (User) (feeStructure.getUser() == null? this.user : feeStructure.getUser());
        	this.status = (String) (feeStructure.getStatus() == null? this.status : feeStructure.getStatus());
        }
    }  
}
