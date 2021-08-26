package com.mnit.erp.academic.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.academic.course.model.CourseExtraCredit;
import com.mnit.erp.academic.course.repository.CourseExtraCreditRepository;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseExtraCreditService {
	@Autowired
	CourseExtraCreditRepository courseExtraCreditRepository;
	
	/* Course ExtraCredit*/
    public CustomResponseMessage courseFindById(Long id) {
    	String sActionRes = "Course ExtraCredit found by id";
    	CourseExtraCredit course = null;
    	try{
            course = this.courseExtraCreditRepository.findById(id).orElse(null);           
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find course ExtraCredit by id";
    	}
        return CommonUtils.buildResponse(course, sActionRes);
    }
    public CustomResponseMessage courseFindAll() {
    	String sActionRes = "Courses ExtraCredit found";
    	List<CourseExtraCredit> courses = null;
    	try{
    		courses = this.courseExtraCreditRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find courses ExtraCredit";
    	}
        return CommonUtils.buildResponse(courses, sActionRes);
    }
    public CustomResponseMessage courseFindAll(CourseExtraCredit  courseExtraCredit) {
    	String sActionRes = "Courses ExtraCredit found";
    	//deptSup, dugc, senate
    	List<CourseExtraCredit> courses = null;
    	try{
    		courses = this.courseExtraCreditRepository.findAsParam(courseExtraCredit);
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find courses ExtraCredit";
    	}
        return CommonUtils.buildResponse(courses, sActionRes);
    }
    public CustomResponseMessage addOrUpdate(CourseExtraCredit courseExtraCredit){
    	String sActionRes = "save";
    	try{
        	if(courseExtraCredit.getId() != null && courseExtraCredit.getId() > 0){
        		sActionRes = "update";
        		CourseExtraCredit courseEx = this.courseExtraCreditRepository.findById(courseExtraCredit.getId()).orElse(null);
        		if(courseEx != null) {
        			CommonUtils.copyNonNullProperties(courseExtraCredit, courseEx);
        			courseExtraCredit = courseEx;
        		}
        	}
        	courseExtraCreditRepository.save(courseExtraCredit);
    		sActionRes = "Course ExtraCredit" + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		courseExtraCredit = null;
    		sActionRes = "Unable to "+ sActionRes +" course ExtraCredit";
    	}
    	return CommonUtils.buildResponse(courseExtraCredit, sActionRes);
    }
}
