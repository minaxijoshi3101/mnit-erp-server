package com.mnit.erp.academic.course.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.common.CourseState;
import com.mnit.erp.user.model.User;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=CourseRegistrationDetail.class)
public class CourseRegistrationDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity=CourseRegistration.class)
	@JoinColumn(name="courseRegistrationId",referencedColumnName="id",nullable=false,updatable=true)
	@JsonIdentityReference(alwaysAsId = true)
	private CourseRegistration courseRegistration;

	@ManyToOne
	@JoinColumn(name="courseScheme",referencedColumnName="id",nullable=false,updatable=true)
	@JsonIdentityReference(alwaysAsId = true)
	private CourseScheme courseScheme;

	@CreationTimestamp
	private Date createdOn;

	@Enumerated(EnumType.STRING)
    private CourseState courseState;
	
	@ManyToOne
	@JoinColumn(name="progAdvUser",referencedColumnName="id",nullable=true,updatable=true)
	@JsonIdentityReference(alwaysAsId = true)
    private User progAdvUser;
	private Date progAdvDate;
	private String progAdvStatus;
	private String progAdvRemarks;
	
	@ManyToOne
	@JoinColumn(name="deanDugcUser",referencedColumnName="id",nullable=true,updatable=true)
	@JsonIdentityReference(alwaysAsId = true)
    private User deanDugcUser;
	private Date deanDugcDate;
	private String deanDugcStatus;
	private String deanDugcRemarks;

	private Long linkId;
	private String docPath;
	private String remark;
	private String status;
	
	@Transient
	private CourseScheme courseSchemeObj;

	public CourseScheme getCourseSchemeObj() {
		courseSchemeObj = new CourseScheme();
		if(!Objects.isNull(courseScheme)) BeanUtils.copyProperties(courseScheme, courseSchemeObj);
		return courseSchemeObj;
	}
 	
	@Transient
	private String progAdvUserName;
	public String getProgAdvUserName() {
		return progAdvUser != null ? progAdvUser.getUsername() : null;
	}
	
	@Transient
	private String deanDugcUserName;
	public String getDeanDugcUserName() {
		return deanDugcUser != null ? deanDugcUser.getUsername() : null;
	}

}