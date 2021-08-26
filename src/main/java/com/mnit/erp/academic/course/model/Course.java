package com.mnit.erp.academic.course.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=Course.class)
public class Course implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="abbreviation",updatable=true,nullable=false,unique=true, length=25)
	private String abbreviation;
	private String name;	

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private CourseProposal courseProposal;
	
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private CourseType courseType;
	
	private String version;
	private Long credits;		
	private Long lectures;
	private Long tutorial;
	private Long practical;
	private Long studio;
	private Boolean status;
	private String syllabus;
	private String reference;
    private String preRequisiteCourseIds;
	@Transient
    private MultipartFile syllabusFile;
	@Transient
	private MultipartFile referenceFile;
	@Transient
	private CourseProposal courseProposalObj;
	
	public CourseProposal getCourseProposalObj() {
		return courseProposal;
	}
	public String getCourseTypeName() {
		return courseType != null ? courseType.getName() : null;
	}

	public Course(Long courseId){
		this.id = courseId;
	}
}