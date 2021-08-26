package com.mnit.erp.academic.course.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.academic.semester.model.Semester;
import com.mnit.erp.academic.student.st.model.Student;
import com.mnit.erp.department.model.Department;
import com.mnit.erp.user.model.User;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=SemesterWithdrawal.class)
public class SemesterWithdrawal implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private Program program;
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private Degree degree;
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private Department department;
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private Semester semester;
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private Student student;
	
	private String academicYear;
	private String withdrawalGround;
	private String withdrawalReason;
	private String documentPath;
	@Transient
    private MultipartFile withdrawalDoc;

	@CreationTimestamp
	private Date createdOn;
	
	private String status;

    private Date pdReviewDate;
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
    private User pdReviewer;
	private String pdStatus;
	private String pdRemark;

    private Date conReviewDate;
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
    private User conReviewer;
	private String conStatus;
	private String conRemark;

    private Date deanReviewDate;
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
    private User deanReviewer;
    private String deanStatus;
    private String deanRemark;
    
    
    @Transient
	private String programName;
	@Transient
	private String degreeName;
	@Transient
	private String departmentName;
	@Transient
	private String semesterName;

	@Transient
	private String studentName;
	@Transient
	private String studentId;

	@Transient
	private String pdReviewerName;
	@Transient
	private String conReviewerName;
	@Transient
	private String deanReviewerName;

	public String getProgramName() {
		return program != null ? program.getName() : null;
	}
	public String getDegreeName() {
		return degree != null ? degree.getName() : null;
	}
	public String getDepartmentName() {
		return department != null ? department.getName() : null;
	}
	public String getSemesterName() {
		return semester != null ? semester.getName() : null;
	}
	public String getStudentName() {
		return student != null ? student.getName() : null;
	}
	public String getStudentId() {
		return student != null ? student.getStudentId() : null;
	}
	public String getPdReviewerName() {
		return pdReviewer != null ? pdReviewer.getUsername() : null;
	}	
	public String getConReviewerName() {
		return conReviewer != null ? conReviewer.getUsername() : null;
	}	
	public String getDeanReviewerName() {
		return deanReviewer != null ? deanReviewer.getUsername() : null;
	}	
}