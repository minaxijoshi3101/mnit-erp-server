package com.mnit.erp.scholarship.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.mnit.erp.academic.student.st.repository.StudentRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.scholarship.model.ApplyType;
import com.mnit.erp.scholarship.model.SanctionType;
import com.mnit.erp.scholarship.model.ScholarshipStudentDetails;
import com.mnit.erp.scholarship.repository.ScholarshipMasterRepository;
import com.mnit.erp.scholarship.repository.ScholarshipStudentDetailsRepository;
import com.mnit.erp.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ScholarshipStudentDetailsService {
	
	@Autowired
	ScholarshipStudentDetailsRepository scholarshipStudentDetailsRepository;
	
	@Autowired
	ScholarshipMasterRepository scholarshipMasterRepository;
	
	@Autowired
    StudentRepository studentRepository;

	public List<ScholarshipStudentDetails> findAll() {		
		return this.scholarshipStudentDetailsRepository.findAll();
	}

	public ScholarshipStudentDetails add(ScholarshipStudentDetails scholarshipStudentDetails) {
		if(validate(scholarshipStudentDetails)) {
			return this.scholarshipStudentDetailsRepository.save(scholarshipStudentDetails);
		}
		return null;
	}

	public ScholarshipStudentDetails update(ScholarshipStudentDetails scholarshipStudentDetails) {
		ScholarshipStudentDetails savedScholarshipStudentDetails= this.scholarshipStudentDetailsRepository.findById(scholarshipStudentDetails.getId()).orElse(null);
		if(Objects.isNull(savedScholarshipStudentDetails)) {
			throw new ServiceException("savedScholarshipStudentDetails doesn't exists,Update not possible! ");
		}
		if(validate(scholarshipStudentDetails)) {
			CommonUtils.copyNonNullProperties(scholarshipStudentDetails, savedScholarshipStudentDetails);
			return this.scholarshipStudentDetailsRepository.save(savedScholarshipStudentDetails);
		}
		return null;
	}

	public ScholarshipStudentDetails find(Long id) {		
		return this.scholarshipStudentDetailsRepository.findById(id).orElse(null);
	}

	public CustomResponseMessage uploadFile(Long Id, String fileType, MultipartFile file) {
		String sActionRes = "File uploaded failed";
		ScholarshipStudentDetails scholarshipStudentDetailsEx = null;
		try{
        	if(Id > 0 && file.getBytes().length > 0){
        		scholarshipStudentDetailsEx = scholarshipStudentDetailsRepository.findById(Id).orElse(null);
        		if(scholarshipStudentDetailsEx != null) {
    	        	if(fileType.equalsIgnoreCase("fundReceiptDoc")) {
    	        		scholarshipStudentDetailsEx.setFundReceiptDoc(CommonUtils.saveFile("Scholarship/fundReceiptDoc", file));
    	        	}
    	        	if(fileType.equalsIgnoreCase("bankStatementCopyDoc")) {
    	        		scholarshipStudentDetailsEx.setBankStatementCopyDoc(CommonUtils.saveFile("Scholarship/bankStatementCopyDoc", file));
    	        	}
    	        	if(fileType.equalsIgnoreCase("semesterGradeSheet")) {
    	        		scholarshipStudentDetailsEx.setSemesterGradeSheet(CommonUtils.saveFile("Scholarship/semesterGradeSheet", file));
    	        	}
    	        	if(fileType.equalsIgnoreCase("BankAdviseDoc")) {
    	        		scholarshipStudentDetailsEx.setBankAdviseDoc(CommonUtils.saveFile("Scholarship/BankAdviseDoc", file));
    	        	}
    	        	if(fileType.equalsIgnoreCase("SanctionLetterDoc")) {
    	        		scholarshipStudentDetailsEx.setSanctionLetterDoc(CommonUtils.saveFile("Scholarship/SanctionLetterDoc", file));
    	        	}   	        	
    	        	scholarshipStudentDetailsRepository.save(scholarshipStudentDetailsEx);
    	    		sActionRes = "File uploaded successfuly";
        		}
        	}
    	}catch(Exception ex){
    		log.error("", ex);
    	
    		scholarshipStudentDetailsEx = null;
    		sActionRes = "Unable to upload file";
    	}
    	return CommonUtils.buildResponse(scholarshipStudentDetailsEx, sActionRes);
	}

	public List<ScholarshipStudentDetails> findByStudentId(Long studentId) {		
		return this.scholarshipStudentDetailsRepository.findByStudent(this.studentRepository.findById(studentId).orElse(null));
	}

	public List<ScholarshipStudentDetails> findAllByScholarshipMasterId(Long scholarshipMasterId) {
		return this.scholarshipStudentDetailsRepository.findByScholarshipMaster(this.scholarshipMasterRepository.findById(scholarshipMasterId).orElse(null));
	}


	private boolean validate(ScholarshipStudentDetails scholarshipStudentDetails) {
		return true;
	}

	public List<ScholarshipStudentDetails> findAllByApplicationType(ApplyType applyType) {		
		return this.scholarshipStudentDetailsRepository.findByApplyType(applyType);
	}

	public List<ScholarshipStudentDetails> findByScholarshipYear(Long year) {
		return this.scholarshipStudentDetailsRepository.findByScholarshipYear(year);
	}

	public List<ScholarshipStudentDetails> findBySanctionYear(Long year) {
		return this.scholarshipStudentDetailsRepository.findBySanctionYear(year);
	}

	public List<ScholarshipStudentDetails> findBySanctionType(SanctionType sanctionType) {
		return this.scholarshipStudentDetailsRepository.findBySanctionType(sanctionType);
	}

	public List<ScholarshipStudentDetails> findByFinancialYear(Long year) {
		return this.scholarshipStudentDetailsRepository.findByFinancialYear(year);
	}

}
