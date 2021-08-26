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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.academic.degree.model.Degree;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=CourseScheme.class)
public class CourseScheme implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private Course course;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private Department department;
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private Degree degree;
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private Semester semester;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private ElectiveType electiveType;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private User coordinator;

	private String academicYear;
	private Long credits;
	private Long lectures;
	private Long tutorial;
	private Long practical;
	private Long studio;
	private Boolean status;
	
    @CreationTimestamp
	private Date createdOn;

    @Transient
	private String courseName;
    @Transient
	private String degreeName;
	@Transient
	private String departmentName;
	@Transient
	private String semesterName;
	@Transient
	private String courseTypeName;
	@Transient
	private String elsectiveTypeName;
	@Transient
	private String coordinatorName;

	public String getCourseName() {
		return course != null ? course.getName() : null;
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
	public String getElectiveTypeName() {
		return electiveType != null ? electiveType.getName() : null;
	}
	public String getCoordinatorName() {
		return coordinator != null ? coordinator.getUsername() : null;
	}
}