package com.mnit.erp.academic.course.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Contains Elective type model
 *
 * @author: Tejpal Singh
 * @date: 22 May, 2021
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=ElectiveType.class)
public class ElectiveType implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String abbreviation;
	private String name;
	private Boolean status;
	
	public void updateJson(ElectiveType electiveType) {
		if(electiveType!=null) {
			this.abbreviation = electiveType.getAbbreviation()==null?this.abbreviation:electiveType.getAbbreviation();
			this.name = electiveType.getName()==null?this.name:electiveType.getName();
			this.status = electiveType.getStatus()==null?this.status:electiveType.getStatus();
		}
	}
}
