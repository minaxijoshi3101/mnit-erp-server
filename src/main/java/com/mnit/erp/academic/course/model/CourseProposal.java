package com.mnit.erp.academic.course.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
//@JsonInclude(Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=CourseProposal.class)
public class CourseProposal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="name",updatable=true,nullable=false,unique=true, length=50)
	private String name;	

	@Column(name="topics",updatable=true,nullable=false,unique=true)
	private String topics;	

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
	private CourseType courseType;
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
    private User proposer;

	private Long credits;
	private String remarks;
    @CreationTimestamp
	private Date createdOn;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private User deptSup;
	private Date deptSupReviewDate;
	private String deptSupStatus;
	private String deptSupRemark;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private User dugc;
	private Date dugcMeetingDate;
	private String dugcStatus;
	private String dugcRemark;
	private String dugcMOMPath;
	
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private User senate;
	private Date senateMeetingDate;
	private String senateStatus;
	private String senateRemark;
	private String senateMOMPath;
	
	@Transient
	private MultipartFile dugcMOMFile;
	@Transient
	private MultipartFile senateMOMFile;

	@Transient
	private String programName;
	@Transient
	private String degreeName;
	@Transient
	private String departmentName;
	@Transient
	private String semesterName;
	@Transient
	private String courseTypeName;
	@Transient
	private String deptSupName;
	@Transient
	private String dugcName;
	@Transient
	private String senateName;
	@Transient
	private String proposerName;

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
	public String getCourseTypeName() {
		return courseType != null ? courseType.getName() : null;
	}
	
	public String getDeptSupName() {
		return deptSup != null ? deptSup.getUsername() : null;
	}
	public String getDugcName() {
		return dugc != null ? dugc.getUsername() : null;
	}
	public String getSenateName() {
		return senate != null ? senate.getUsername() : null;
	}
	public String getProposerName() {
		return proposer != null ? proposer.getUsername() : null;
	}
}