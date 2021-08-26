package com.mnit.erp.academic.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mnit.erp.academic.course.model.SemesterWithdrawal;
import com.mnit.erp.academic.course.repository.ElectiveTypeRepository;
import com.mnit.erp.academic.course.repository.SemesterWithdrawalRepository;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SemesterWithdrawalService {
	@Autowired
	SemesterWithdrawalRepository semesterWithdrawalRepository;
	
	@Autowired
	ElectiveTypeRepository electiveTypeRepository;
	
	/* Course */
    public CustomResponseMessage findById(Long id) {
    	String sActionRes = "SemesterWithdrawal found by id";
    	SemesterWithdrawal semesterWithdrawal = null;
    	try{
    		semesterWithdrawal = this.semesterWithdrawalRepository.findById(id).orElse(null);           
    	}catch(Exception ex){
    		log.error("",ex);
    		semesterWithdrawal = null;
    		sActionRes = "Unable to find semesterWithdrawal by id";
    	}
        return CommonUtils.buildResponse(semesterWithdrawal, sActionRes);
    }
    public CustomResponseMessage findAll() {
    	String sActionRes = "SemesterWithdrawal found";
    	List<SemesterWithdrawal> semesterWithdrawals = null;
    	try{
    		semesterWithdrawals = this.semesterWithdrawalRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		semesterWithdrawals = null;
    		sActionRes = "Unable to find SemesterWithdrawals";
    	}
        return CommonUtils.buildResponse(semesterWithdrawals, sActionRes);
    }

    public CustomResponseMessage addOrUpdate(SemesterWithdrawal semesterWithdrawal){
    	String sActionRes = "save";
    	try{
        	if(semesterWithdrawal.getId() != null && semesterWithdrawal.getId() > 0){
        		sActionRes = "update";
        		SemesterWithdrawal semesterWithdrawalEx = this.semesterWithdrawalRepository.findById(semesterWithdrawal.getId()).orElse(null);
        		if(semesterWithdrawalEx != null) {
        			CommonUtils.copyNonNullProperties(semesterWithdrawal, semesterWithdrawalEx);
        			semesterWithdrawal = semesterWithdrawalEx;
        		}
        	}
        	//if syllabus and reference file has values then save the file
        	if(semesterWithdrawal.getWithdrawalDoc() != null && semesterWithdrawal.getWithdrawalDoc().getBytes().length > 0) {
        		semesterWithdrawal.setDocumentPath(CommonUtils.saveFile("semesterWithdrawal/withdrawalDoc", semesterWithdrawal.getWithdrawalDoc()));
        	}
        	semesterWithdrawalRepository.save(semesterWithdrawal);
    		sActionRes = "Semester Withdrawal " + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		semesterWithdrawal = null;
    		sActionRes = "Unable to "+ sActionRes +" Semester Withdrawal";
    	}
    	return CommonUtils.buildResponse(semesterWithdrawal, sActionRes);
    }
	
    public CustomResponseMessage uploadFile(Long semesterWithdrawalId,String fileType,MultipartFile file){
    	String sActionRes = "File uploaded failed";
    	SemesterWithdrawal semesterWithdrawalEx = null;
		try{
        	if(semesterWithdrawalId > 0 && file.getBytes().length > 0){
        		semesterWithdrawalEx = semesterWithdrawalRepository.findById(semesterWithdrawalId).orElse(null);
        		if(semesterWithdrawalEx != null) {
    	        	//if syllabus and reference file has values then save the file
    	        	if(fileType.equalsIgnoreCase("releventDocs")) {
    	        		semesterWithdrawalEx.setDocumentPath(CommonUtils.saveFile("semesterWithdrawal/withdrawalDoc", file));
    	        	}
    	        	semesterWithdrawalRepository.save(semesterWithdrawalEx);
    	    		sActionRes = "File uploaded successfuly";
        		}
        	}
    	}catch(Exception ex){
    		log.error("",ex);
    		semesterWithdrawalEx = null;
    		sActionRes = "Unable to upload file";
    	}
    	return CommonUtils.buildResponse(semesterWithdrawalEx, sActionRes);
    }
    
    public CustomResponseMessage findAsParam(SemesterWithdrawal  semesterWithdrawal) {
    	String sActionRes = "Semester Withdrawal found";
    	List<SemesterWithdrawal> semesterWithdrawals = null;
    	try{
    		semesterWithdrawals = this.semesterWithdrawalRepository.findAsParam(semesterWithdrawal);
    	}catch(Exception ex){
    		log.error("",ex);
    		semesterWithdrawals = null;
    		sActionRes = "Unable to find Semester Withdrawal";
    	}
        return CommonUtils.buildResponse(semesterWithdrawals, sActionRes);
    }

}
