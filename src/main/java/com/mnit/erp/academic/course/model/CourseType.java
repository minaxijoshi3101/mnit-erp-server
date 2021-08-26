package com.mnit.erp.academic.course.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.util.EntityIdResolver;

import lombok.Data;
/**
 * Contains Course type model
 *
 * @author: Tejpal Singh
 * @date: 22 May, 2021
 */
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=CourseType.class)
public class CourseType implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String abbreviation;
	private String name;
	private Boolean status;
	
	public void updateJson(CourseType courseType) {
		if(courseType!=null) {
			this.abbreviation = courseType.getAbbreviation()==null?this.abbreviation:courseType.getAbbreviation();
			this.name = courseType.getName()==null?this.name:courseType.getName();
			this.status = courseType.getStatus()==null?this.status:courseType.getStatus();
		}
	}
}
