package com.mnit.erp.scholarship.model;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=ScholarshipType.class)
public class ScholarshipType {

	 public ScholarshipType(Long scholarshipTypeId) {
		this.id = scholarshipTypeId;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long id;
	
	 private String type;
	 
	
	 	
}
