package com.mnit.erp.scholarship.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.mnit.erp.academic.student.st.model.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ScholarshipStudentDetails {
	 @Id 
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(nullable = false)
	 Student student;
	 
	 @Transient
	 Long studentId;
	 
	 public void setStudentId(Long studentId){
	        this.student = Objects.nonNull(studentId) ? new Student(studentId) : null;
	    }
	 
	 public Long getStudentId(){
	        return Objects.nonNull(this.student) ? this.student.getId() : null;
	    }
	 
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "scholarshipMaster_id", foreignKey = @ForeignKey(name="scholarshipMaster_id_fk"), nullable = false)
	 private ScholarshipMaster scholarshipMaster;
	 
	 @Transient
	 Long scholarshipMasterId;
	 
	 public void setScholarshipMasterId(Long scholarshipMasterId){
	        this.scholarshipMaster = Objects.nonNull(scholarshipMasterId) ? new ScholarshipMaster(scholarshipMasterId) : null;
	    }
	 
	 public Long getScholarshipMasterId(){
	        return Objects.nonNull(this.scholarshipMaster) ? this.scholarshipMaster.getId() : null;
	    }
	 
	 private String fundReceiptDoc;
	 private String bankStatementCopyDoc;
	 private String semesterGradeSheet;
	 
	 private String bankAccountNo;
	 private String bankifsc;
	 private String bankName;
	 
	 private String BankAdviseDoc;
	 private String SanctionLetterDoc;
	 
	 private String paymentMode;
	 private long scholarshipYear;
	 private long sanctionYear;
	 
	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	 private Date sanctionDate;
	 
	 private ApplyType applyType;
	 private long Amount;
	 
	 private long financialYear;
	 
	 private SanctionType sanctionType;
	 
	 
	 
	 

}
