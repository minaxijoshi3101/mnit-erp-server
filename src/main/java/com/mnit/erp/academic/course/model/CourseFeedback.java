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
import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.academic.semester.model.Semester;
import com.mnit.erp.academic.student.st.model.Student;
import com.mnit.erp.department.model.Department;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=CourseFeedback.class)
public class CourseFeedback implements Serializable{
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
	@CreationTimestamp
	private Date createdOn;

    private String academicYear;
    private String feedbackAtmosphere;
    private String feedbackPersonalGrowth;
    private String feedbackAdministration;
    private String suggestionComplain;
    
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
}