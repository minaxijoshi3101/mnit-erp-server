package com.mnit.erp.scholarship.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mnit.erp.academic.program.repository.ProgramRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.scholarship.model.ScholarshipMaster;
import com.mnit.erp.scholarship.repository.ScholarshipMasterRepository;
import com.mnit.erp.scholarship.repository.ScholarshipTypeRepository;
import com.mnit.erp.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ScholarshipMasterService {

	@Autowired
	ScholarshipMasterRepository scholarshipMasterRepository;
	
	@Autowired
	ScholarshipTypeRepository scholarshipTypeRepository;
	
	@Autowired
	ProgramRepository programRepository;


	public List<ScholarshipMaster> findAll() {
		
		return this.scholarshipMasterRepository.findAll();
	}

	public ScholarshipMaster add(ScholarshipMaster scholarshipMaster) {
		if(validate(scholarshipMaster)) {
			
			
			return this.scholarshipMasterRepository.save(scholarshipMaster);
		}
		return null;
	}

	public ScholarshipMaster update(ScholarshipMaster scholarshipMaster) {
		ScholarshipMaster savedScholarshipMaster= this.scholarshipMasterRepository.findById(scholarshipMaster.getId()).orElse(null);
		if(Objects.isNull(savedScholarshipMaster)) {
			throw new ServiceException("scholarshipMaster doesn't exists,Update not possible! ");
		}
		if(validate(scholarshipMaster)) {
			CommonUtils.copyNonNullProperties(scholarshipMaster, savedScholarshipMaster);
			return this.scholarshipMasterRepository.save(savedScholarshipMaster);
		}
		return null;
	}



	public ScholarshipMaster find(Long id) {
		return this.scholarshipMasterRepository.findById(id).orElse(null);
	}

	public CustomResponseMessage uploadFile(Long Id, String fileType, MultipartFile file) {
		String sActionRes = "File uploaded failed";
		ScholarshipMaster scholarshipMasterEx = null;
		try{
        	if(Id > 0 && file.getBytes().length > 0){
        		scholarshipMasterEx = scholarshipMasterRepository.findById(Id).orElse(null);
        		if(scholarshipMasterEx != null) {
    	        	if(fileType.equalsIgnoreCase("NormsDoc")) {
    	        		scholarshipMasterEx.setNormsDoc(CommonUtils.saveFile("Scholarship/NormsDoc", file));
    	        	}
  
    	        	scholarshipMasterRepository.save(scholarshipMasterEx);
    	    		sActionRes = "File uploaded successfuly";
        		}
        	}
    	}catch(Exception ex){
    		log.error("", ex);
    	
    		scholarshipMasterEx = null;
    		sActionRes = "Unable to upload file";
    	}
    	return CommonUtils.buildResponse(scholarshipMasterEx, sActionRes);
	}
	
	private boolean validate(ScholarshipMaster scholarshipMaster) {
		return true;
	}



	public List<ScholarshipMaster> findByScholarshipNames(String name) {
		return this.scholarshipMasterRepository.findByScholarshipName(name);
	}

	public List<ScholarshipMaster> findAllByScholarshipMasterId(Long id) {
		
		return this.scholarshipMasterRepository.findByScholarshipType(this.scholarshipTypeRepository.findById(id).orElse(null));
	}

	public List<ScholarshipMaster> findAllByProgram(Long id) {
		return this.scholarshipMasterRepository.findByProgram(this.programRepository.findById(id).orElse(null));
	}

	

	
}
