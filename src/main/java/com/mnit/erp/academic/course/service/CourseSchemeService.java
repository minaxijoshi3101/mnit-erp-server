package com.mnit.erp.academic.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.academic.course.model.CourseScheme;
import com.mnit.erp.academic.course.repository.CourseSchemeRepository;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseSchemeService {
	@Autowired
	CourseSchemeRepository courseSchemeRepository;
	
	/* Course Proposal*/
    public CustomResponseMessage courseFindById(Long id) {
    	String sActionRes = "Course scheme found by id";
    	CourseScheme course = null;
    	try{
            course = this.courseSchemeRepository.findById(id).orElse(null);           
    	}catch(Exception ex){
    		log.error("",ex);
    		course = null;
    		sActionRes = "Unable to find course scheme by id";
    	}
        return CommonUtils.buildResponse(course, sActionRes);
    }
    public CustomResponseMessage courseFindAll() {
    	String sActionRes = "Courses scheme found";
    	List<CourseScheme> courses = null;
    	try{
    		courses = this.courseSchemeRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		courses = null;
    		sActionRes = "Unable to find courses scheme";
    	}
        return CommonUtils.buildResponse(courses, sActionRes);
    }
    public CustomResponseMessage courseFindAll(CourseScheme  courseScheme) {
    	String sActionRes = "Courses scheme found";
    	//deptSup, dugc, senate
    	List<CourseScheme> courses = null;
    	try{
    		courses = this.courseSchemeRepository.findAsParam(courseScheme);
    	}catch(Exception ex){
    		log.error("",ex);
    		courses = null;
    		sActionRes = "Unable to find courses scheme";
    	}
        return CommonUtils.buildResponse(courses, sActionRes);
    }
    public CustomResponseMessage addOrUpdate(CourseScheme courseScheme){
    	String sActionRes = "save";
    	try{
        	if(courseScheme.getId() != null && courseScheme.getId() > 0){
        		sActionRes = "update";
        		CourseScheme courseEx = this.courseSchemeRepository.findById(courseScheme.getId()).orElse(null);
        		if(courseEx != null) {
        			CommonUtils.copyNonNullProperties(courseScheme, courseEx);
        			courseScheme = courseEx;
        		}
        	}
        	courseSchemeRepository.save(courseScheme);
    		sActionRes = "Course scheme" + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		courseScheme = null;
    		sActionRes = "Unable to "+ sActionRes +" course scheme";
    	}
    	return CommonUtils.buildResponse(courseScheme, sActionRes);
    }
}
