package com.mnit.erp.academic.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mnit.erp.academic.course.model.Course;
import com.mnit.erp.academic.course.model.CourseType;
import com.mnit.erp.academic.course.model.ElectiveType;
import com.mnit.erp.academic.course.repository.CourseRepository;
import com.mnit.erp.academic.course.repository.CourseTypeRepository;
import com.mnit.erp.academic.course.repository.ElectiveTypeRepository;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	CourseTypeRepository courseTypeRepository;
	
	@Autowired
	ElectiveTypeRepository electiveTypeRepository;
	
	/* Course */
    public CustomResponseMessage courseFindById(Long id) {
    	String sActionRes = "Course found by id";
    	Course course = null;
    	try{
            course = this.courseRepository.findById(id).orElse(null);           
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find course by id";
    	}
        return CommonUtils.buildResponse(course, sActionRes);
    }
    public CustomResponseMessage courseFindAll() {
    	String sActionRes = "Courses found";
    	List<Course> courses = null;
    	try{
    		courses = this.courseRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find courses";
    	}
        return CommonUtils.buildResponse(courses, sActionRes);
    }

    public CustomResponseMessage addOrUpdate(Course course){
    	String sActionRes = "save";
    	try{
        	if(course.getId() != null && course.getId() > 0){
        		sActionRes = "update";
        		Course courseEx = this.courseRepository.findById(course.getId()).orElse(null);
        		if(courseEx != null) {
        			CommonUtils.copyNonNullProperties(course, courseEx);
        			course = courseEx;
        		}
        	}
        	//if syllabus and reference file has values then save the file
        	if(course.getSyllabusFile() != null && course.getSyllabusFile().getBytes().length > 0) {
        		course.setSyllabus(CommonUtils.saveFile("course/syllabus", course.getSyllabusFile()));
        	}
        	if(course.getReferenceFile() != null && course.getReferenceFile().getBytes().length > 0) {
        		course.setReference(CommonUtils.saveFile("course/reference", course.getSyllabusFile()));
        	}
        	courseRepository.save(course);
    		sActionRes = "Course " + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		course = null;
    		sActionRes = "Unable to "+ sActionRes +" course proposal";
    	}
    	return CommonUtils.buildResponse(course, sActionRes);
    }
	
    /* Course Type */
    public CustomResponseMessage courseTypeFindById(Long id) {
    	String sActionRes = "Course type found by id";
    	CourseType courseType = null;
    	try{
            courseType = this.courseTypeRepository.findById(id).orElse(null);
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find course type by id";
    	}
        return CommonUtils.buildResponse(courseType, sActionRes);
    }
    public CustomResponseMessage courseTypeFindAll() {
    	String sActionRes = "Course types found";
    	List<CourseType> courseTypes = null;
    	try{
    		courseTypes = this.courseTypeRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find course types";
    	}
        return CommonUtils.buildResponse(courseTypes, sActionRes);
    }
    public CustomResponseMessage addOrUpdate(CourseType courseType){
    	String sActionRes = "save";
    	try{
        	if(courseType.getId() != null && courseType.getId() > 0){
        		sActionRes = "update";
        		CourseType courseTypeEx = this.courseTypeRepository.findById(courseType.getId()).orElse(null);
        		if(courseTypeEx != null) courseTypeEx.updateJson(courseType);
        	}
        	courseTypeRepository.save(courseType);
    		sActionRes = "Course type " + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		courseType = null;
    		sActionRes = "Unable to "+ sActionRes +" course type ";
    	}
    	return CommonUtils.buildResponse(courseType, sActionRes);
    }

    /* Elective Type */
    public CustomResponseMessage electiveTypeFindById(Long id) {
    	String sActionRes = "Elective type found by id";
    	ElectiveType electiveType = null;
    	try{
            electiveType = this.electiveTypeRepository.findById(id).orElse(null);
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find elective type by id";
    	}
        return CommonUtils.buildResponse(electiveType, sActionRes);
    }
    public CustomResponseMessage electiveTypeFindAll() {
    	String sActionRes = "Elective types found";
    	List<ElectiveType> electiveTypes = null;
    	try{
    		electiveTypes = this.electiveTypeRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find elective types";
    	}
        return CommonUtils.buildResponse(electiveTypes, sActionRes);
    }
    public CustomResponseMessage addOrUpdate(ElectiveType electiveType){
    	String sActionRes = "save";
    	try{
        	if(electiveType.getId() != null && electiveType.getId() > 0){
        		sActionRes = "update";
        		ElectiveType electiveTypeEx = this.electiveTypeRepository.findById(electiveType.getId()).orElse(null);
        		if(electiveTypeEx != null) electiveTypeEx.updateJson(electiveType);
        	}
        	electiveTypeRepository.save(electiveType);
    		sActionRes = "Elective type " + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		electiveType = null;
    		sActionRes = "Unable to "+ sActionRes +" elective type ";
    	}
    	return CommonUtils.buildResponse(electiveType, sActionRes);
    }
    
    public CustomResponseMessage uploadFile(Long courseId,String fileType,MultipartFile file){
    	String sActionRes = "File uploaded failed";
		Course courseEx = null;
		try{
        	if(courseId > 0 && file.getBytes().length > 0){
        		courseEx = courseRepository.findById(courseId).orElse(null);
        		if(courseEx != null) {
    	        	//if syllabus and reference file has values then save the file
    	        	if(fileType.equalsIgnoreCase("syllabus")) {
    	        		courseEx.setSyllabus(CommonUtils.saveFile("course/syllabus", file));
    	        	}
    	        	if(fileType.equalsIgnoreCase("reference")) {
    	        		courseEx.setReference(CommonUtils.saveFile("course/reference", file));
    	        	}
    	        	courseRepository.save(courseEx);
    	    		sActionRes = "File uploaded successfuly";
        		}
        	}
    	}catch(Exception ex){
    		log.error("",ex);
    		courseEx = null;
    		sActionRes = "Unable to upload file";
    	}
    	return CommonUtils.buildResponse(courseEx, sActionRes);
    }
}
