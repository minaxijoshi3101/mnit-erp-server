package com.mnit.erp.academic.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mnit.erp.academic.course.model.CourseProposal;
import com.mnit.erp.academic.course.repository.CourseProposalRepository;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseProposalService {
	@Autowired
	CourseProposalRepository courseProposalRepository;
	
	/* Course Proposal*/
    public CustomResponseMessage courseFindById(Long id) {
    	String sActionRes = "Course proposal found by id";
    	CourseProposal course = null;
    	try{
            course = this.courseProposalRepository.findById(id).orElse(null);           
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find course proposal by id";
    	}
        return CommonUtils.buildResponse(course, sActionRes);
    }
    public CustomResponseMessage courseFindAll() {
    	String sActionRes = "Courses proposal found";
    	List<CourseProposal> courses = null;
    	try{
    		courses = this.courseProposalRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find courses proposal";
    	}
        return CommonUtils.buildResponse(courses, sActionRes);
    }
    public CustomResponseMessage courseFindAll(CourseProposal  courseProposal) {
    	String sActionRes = "Courses proposal found";
    	//deptSup, dugc, senate
    	List<CourseProposal> courses = null;
    	try{
    		courses = this.courseProposalRepository.findAsParam(courseProposal);
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find courses proposal";
    	}
        return CommonUtils.buildResponse(courses, sActionRes);
    }
    public CustomResponseMessage addOrUpdate(CourseProposal courseProposal){
    	String sActionRes = "save";
    	try{
        	if(courseProposal.getId() != null && courseProposal.getId() > 0){
        		sActionRes = "update";
        		CourseProposal courseEx = this.courseProposalRepository.findById(courseProposal.getId()).orElse(null);
        		if(courseEx != null) {
        			CommonUtils.copyNonNullProperties(courseProposal, courseEx);
        			courseProposal = courseEx;
        		}
        	}
        	//if syllabus and reference file has values then save the file
			
			if (courseProposal.getDugcMOMFile() != null && courseProposal.getDugcMOMFile().getBytes().length > 0) {
				courseProposal.setDugcMOMPath(CommonUtils.saveFile("course/dugcMOM", courseProposal.getDugcMOMFile()));
			}
			if (courseProposal.getSenateMOMFile() != null && courseProposal.getSenateMOMFile().getBytes().length > 0) {
				courseProposal.setSenateMOMPath(CommonUtils.saveFile("course/senateMOM", courseProposal.getSenateMOMFile()));
			}
			         	
        	courseProposalRepository.save(courseProposal);
    		sActionRes = "Course proposal" + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		courseProposal = null;
    		sActionRes = "Unable to "+ sActionRes +" course proposal";
    	}
    	return CommonUtils.buildResponse(courseProposal, sActionRes);
    }
    public CustomResponseMessage uploadFile(Long courseId,String fileType,MultipartFile file){
    	String sActionRes = "File uploaded failed";
		CourseProposal courseEx = null;
		try{
        	if(courseId > 0 && file.getBytes().length > 0){
        		courseEx = courseProposalRepository.findById(courseId).orElse(null);
        		if(courseEx != null) {
    	        	//if syllabus and reference file has values then save the file
    	        	if(fileType.equalsIgnoreCase("dugcMOM")) {
    	        		courseEx.setDugcMOMPath(CommonUtils.saveFile("course/dugcMOM", file));
    	        	}
    	        	if(fileType.equalsIgnoreCase("senateMOM")) {
    	        		courseEx.setSenateMOMPath(CommonUtils.saveFile("course/senateMOM", file));
    	        	}
    	        	courseProposalRepository.save(courseEx);
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
