package com.mnit.erp.academic.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.academic.course.model.CourseFeedback;
import com.mnit.erp.academic.course.repository.CourseFeedbackRepository;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseFeedbackService {
	@Autowired
	CourseFeedbackRepository courseFeedbackRepository;
	
	/* Course Proposal*/
    public CustomResponseMessage findById(Long id) {
    	String sActionRes = "Course Feedback found by id";
    	CourseFeedback courseFeedback = null;
    	try{
    		courseFeedback = this.courseFeedbackRepository.findById(id).orElse(null);           
    	}catch(Exception ex){
    		log.error("",ex);
    		courseFeedback = null;
    		sActionRes = "Unable to find course feedback by id";
    	}
        return CommonUtils.buildResponse(courseFeedback, sActionRes);
    }
    public CustomResponseMessage findAll() {
    	String sActionRes = "Course Feedback scheme found";
    	List<CourseFeedback> courseFeedbacks = null;
    	try{
    		courseFeedbacks = this.courseFeedbackRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		courseFeedbacks= null;
    		sActionRes = "Unable to find course feedback";
    	}
        return CommonUtils.buildResponse(courseFeedbacks, sActionRes);
    }
    public CustomResponseMessage findAsParam(CourseFeedback  courseFeedback) {
    	String sActionRes = "course feedback found";
    	//deptSup, dugc, senate
    	List<CourseFeedback> courseFeedbacks = null;
    	try{
    		courseFeedbacks = this.courseFeedbackRepository.findAsParam(courseFeedback);
    	}catch(Exception ex){
    		log.error("",ex);
    		courseFeedbacks=null;
    		sActionRes = "Unable to find course feedback";
    	}
        return CommonUtils.buildResponse(courseFeedbacks, sActionRes);
    }
    public CustomResponseMessage addOrUpdate(CourseFeedback courseFeedback){
    	String sActionRes = "save";
    	try{
        	if(courseFeedback.getId() != null && courseFeedback.getId() > 0){
        		sActionRes = "update";
        		CourseFeedback courseFeedbackEx = this.courseFeedbackRepository.findById(courseFeedback.getId()).orElse(null);
        		if(courseFeedbackEx != null) {
        			CommonUtils.copyNonNullProperties(courseFeedback, courseFeedbackEx);
        			courseFeedback = courseFeedbackEx;
        		}
        	}
        	courseFeedbackRepository.save(courseFeedback);
    		sActionRes = "Course feedback " + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		courseFeedback = null;
    		sActionRes = "Unable to "+ sActionRes +" course feedback";
    	}
    	return CommonUtils.buildResponse(courseFeedback, sActionRes);
    }
}
