package com.mnit.erp.scholarship.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mnit.erp.exceptions.ServiceException;

import com.mnit.erp.scholarship.model.ScholarshipType;
import com.mnit.erp.scholarship.repository.ScholarshipTypeRepository;
import com.mnit.erp.util.CommonUtils;

@Service
public class ScholarshipTypeService {
	
	@Autowired
	ScholarshipTypeRepository scholarshipTypeRepository;
	

	public List<ScholarshipType> findAll() {
		
		return this.scholarshipTypeRepository.findAll();
	}

	public ScholarshipType add(ScholarshipType scholarshipType) {
		if(validate(scholarshipType)) {
			return this.scholarshipTypeRepository.save(scholarshipType);
		}
		return null;
	}

	public ScholarshipType update(ScholarshipType scholarshipType) {
		ScholarshipType savedScholarshipType= this.scholarshipTypeRepository.findById(scholarshipType.getId()).orElse(null);
		if(Objects.isNull(savedScholarshipType)) {
			throw new ServiceException("scholarshipMaster doesn't exists,Update not possible! ");
		}
		if(validate(scholarshipType)) {
			CommonUtils.copyNonNullProperties(scholarshipType, savedScholarshipType);
			return this.scholarshipTypeRepository.save(savedScholarshipType);
		}
		return null;
	}

	public ScholarshipType find(Long id) {	
		return this.scholarshipTypeRepository.findById(id).orElse(null);
	}


	public List<ScholarshipType> findByScholarshipTypeName(String name) {
		return this.scholarshipTypeRepository.findByType(name);
	}
	
	private boolean validate(ScholarshipType scholarshipType) {
		return true;
	}
	
}
