package com.mnit.erp.academic.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mnit.erp.academic.course.model.CourseRegistration;
import com.mnit.erp.academic.course.model.CourseRegistrationDetail;
import com.mnit.erp.academic.course.repository.CourseRegistrationDetailRepository;
import com.mnit.erp.academic.course.repository.CourseRegistrationRepository;
import com.mnit.erp.common.CourseState;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseRegistrationService {
	@Autowired
	CourseRegistrationRepository courseRegistrationRepository;
	
	@Autowired
	CourseRegistrationDetailRepository courseRegistrationDetailRepository;

	/* Course Registration*/
    public CustomResponseMessage courseFindById(Long id) {
    	String sActionRes = "Course Registration found by id";
    	CourseRegistration course = null;
    	try{
            course = this.courseRegistrationRepository.findById(id).orElse(null);           
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find course Registration by id";
    	}
        return CommonUtils.buildResponse(course, sActionRes);
    }
    public CustomResponseMessage courseFindAll() {
    	String sActionRes = "Courses Registration found";
    	List<CourseRegistration> courses = null;
    	try{
    		courses = this.courseRegistrationRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find courses Registration";
    	}
        return CommonUtils.buildResponse(courses, sActionRes);
    }
    public CustomResponseMessage findAsParam(CourseRegistration  courseRegistration) {
    	String sActionRes = "Courses Registration found";
    	//deptSup, dugc, senate
    	List<CourseRegistration> courses = null;
    	try{
    		courses = this.courseRegistrationRepository.findAsParam(courseRegistration);
    		if(courseRegistration.getCourseRegistrationDetails() != null && courseRegistration.getCourseRegistrationDetails().size() > 0) {
        		CourseRegistrationDetail cd = courseRegistration.getCourseRegistrationDetails().get(0);
    			for(CourseRegistration cr : courses) {
    				cr.setCourseRegistrationDetails(null);
    				cd.setCourseRegistration(cr);
        			cr.setCourseRegistrationDetails(courseRegistrationDetailRepository.findAsParam(cd));
    			}
    		}
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find courses Registration";
    	}
        return CommonUtils.buildResponse(courses, sActionRes);
    }
    public CustomResponseMessage addOrUpdate(CourseRegistration courseRegistration){
    	String sActionRes = "save";
    	CourseRegistration crSaved = null;
    	try{
        	if(courseRegistration.getId() != null && courseRegistration.getId() > 0){
        		sActionRes = "update";
        		CourseRegistration courseEx = this.courseRegistrationRepository.findById(courseRegistration.getId()).orElse(null);
        		if(courseEx != null) {
        			CommonUtils.copyNonNullProperties(courseRegistration, courseEx);
    				List<CourseRegistrationDetail> cRegExs = courseRegistrationDetailRepository.findByCourseRegistration_Id(courseRegistration.getId());
	            	for(CourseRegistrationDetail cReg : courseEx.getCourseRegistrationDetails()) {
		            	for(CourseRegistrationDetail cRegEx : cRegExs) {
		            		if(cRegEx.getId() == cReg.getId()) {
		            			CommonUtils.copyNonNullProperties(cReg, cRegEx);
		            			if(cReg.getProgAdvUser() != null && cReg.getProgAdvStatus() != null && cRegEx.getProgAdvDate() == null) {
		            				cRegEx.setProgAdvDate(new Date());
		            				if(cRegEx.getCourseState().equals(CourseState.REGISTER) ){
			            				cRegEx.setStatus("Active");
			        	            	for(CourseRegistrationDetail cRegIn : cRegExs) {
			        	            		if(cRegIn.getId() != cRegEx.getId() && cRegIn.getCourseScheme().equals(cRegEx.getCourseScheme()) && "Active".equals(cRegIn.getStatus()) )
			        	            			cRegIn.setStatus("InActive");
			        	            	}
		            				}
		            				if(!"Approve".equalsIgnoreCase(cReg.getProgAdvStatus())) cRegEx.setStatus("InActive");
		            			}
		            			if(cReg.getDeanDugcUser() != null && cReg.getDeanDugcStatus() != null && "Approve".equals(cRegEx.getProgAdvStatus())) {
		            				cRegEx.setDeanDugcDate(new Date());
		            				if("Approve".equals(cReg.getDeanDugcStatus())) {
			            				cRegEx.setStatus("Active");
			            				if(cReg.getLinkId() != null && cReg.getLinkId() > 0) {
				        	            	for(CourseRegistrationDetail cRegIn : cRegExs) {
				        	            		if(cRegIn.getId() == cReg.getLinkId())
				        	            			cRegIn.setCourseState(CourseState.SUBSTITUTE_DROP); 
				        	            		    cRegIn.setStatus("InActive");
				        	            	}
			            				}
			        	            	for(CourseRegistrationDetail cRegIn : cRegExs) {
			        	            		if(cRegIn.getId() != cRegEx.getId() && cRegIn.getCourseScheme().equals(cRegEx.getCourseScheme()) && "Active".equals(cRegIn.getStatus()) )
			        	            			cRegIn.setStatus("InActive");
			        	            	}
		            				}else {
		            					cRegEx.setStatus("InActive");
		            				}
		            			}
		            		}
		            	}
	            	}
	            	for(CourseRegistrationDetail cReg : courseRegistration.getCourseRegistrationDetails()) {
	            		cReg.setCourseRegistration(courseRegistration);
	            		cReg.setCourseSchemeObj(cReg.getCourseScheme());
	            		if(cReg.getId() == null) { 
	            			cReg.setStatus("InProcess");
	            			cRegExs.add(cReg);
	            		}
	            	}
	            	courseEx.setCourseRegistrationDetails(cRegExs);
	            	crSaved = courseRegistrationRepository.save(courseEx);
        		}
        	}else {
        		for(CourseRegistrationDetail cReg : courseRegistration.getCourseRegistrationDetails()) {
        			cReg.setCourseRegistration(courseRegistration);
            		cReg.setCourseSchemeObj(cReg.getCourseScheme());
        			cReg.setStatus("InProcess");
        		}
        		crSaved = courseRegistrationRepository.save(courseRegistration);
        	}
    		sActionRes = "Course Registration " + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		crSaved = null;
    		sActionRes = "Unable to "+ sActionRes +" course Registration";
    	}
    	return CommonUtils.buildResponse(crSaved, sActionRes);
    }
    public CustomResponseMessage uploadFile(Long regDetailId,String fileType,MultipartFile file){
    	String sActionRes = "File uploaded failed";
		CourseRegistrationDetail regDetail = null;
		try{
        	if(regDetailId > 0 && file.getBytes().length > 0){
        		regDetail = courseRegistrationDetailRepository.findById(regDetailId).orElse(null);
        		if(regDetail != null) {
    	        	//if syllabus and reference file has values then save the file
        			regDetail.setDocPath(CommonUtils.saveFile("course/registration"  , file));
    	    		sActionRes = "File uploaded successfuly";
        		}
        	}
    	}catch(Exception ex){
    		log.error("",ex);
    		regDetail = null;
    		sActionRes = "Unable to upload file";
    	}
    	return CommonUtils.buildResponse(regDetail, sActionRes);
    }
}
