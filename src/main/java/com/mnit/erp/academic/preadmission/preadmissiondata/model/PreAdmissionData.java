package com.mnit.erp.academic.preadmission.preadmissiondata.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.academic.admissionType.model.AdmissionType;
import com.mnit.erp.academic.branch.model.Branch;
import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.academic.selectionboard.model.SelectionBoard;
import com.mnit.erp.academic.specialization.model.Specialization;
import com.mnit.erp.category.model.Category;
import com.mnit.erp.common.Gender;
import com.mnit.erp.common.Pwd;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author praha
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints={
   @UniqueConstraint(columnNames={"year", "selection_board", "roll_no"})
})

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=PreAdmissionData.class)
public class PreAdmissionData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="selection_board", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private SelectionBoard selectionBoard;
    
    @Column(name = "year")
    private int year;    
    
    @Column(name = "roll_no")
    private Long rollNo;    
    
    private String name;   
    
    private String fatherName;
    
    private String motherName;
    
    private String dob;
    
    @Enumerated(EnumType.STRING)
    private Gender gender; 
    
    private String categoryValue;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Category category; 
    
    @Enumerated(EnumType.STRING)
    private Pwd pwd;
    
    private String admitCategoryValue;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admit_category", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Category admitCategory; 
    
    @Enumerated(EnumType.STRING)
    private Pwd admitPwd;
    
    private String admitQuota;
    
    private String branchValue;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Branch branch; 
    
    private String degreeValue;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "degree", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Degree degree; 
    
    private String specializationValue;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialization", nullable = true, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Specialization specialization; 
    
    private String admissionTypeValue;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admission_type",nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private AdmissionType admissionType; 
    
    private String sponsoredBy;
    
    private Long air;
    
    private Double score;
    //private String uploadId;
    private String email;
    private String studentStatus;
    private Long mobile;

    private Integer examYear;
    private String examType;
    private String program;
    private String department;
    private String feePayment;
} 
