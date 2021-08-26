package com.mnit.erp.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mnit.erp.academic.course.model.CourseScheme;
import com.mnit.erp.common.model.BankDetail;
import com.mnit.erp.common.model.SlotOpen;
import com.mnit.erp.common.repository.SlotOpenRepository;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SlotOpenService {

    @Autowired
    SlotOpenRepository slotOpenRepository;

    public CustomResponseMessage findById(Long id) {
    	String sActionRes = "Slot open found by id";
    	SlotOpen slotOpen = null;
    	try{
    		slotOpen = this.slotOpenRepository.findById(id).orElse(null);           
    	}catch(Exception ex){
    		log.error("",ex);
    		slotOpen = null;
    		sActionRes = "Unable to find Slot open by id";
    	}
        return CommonUtils.buildResponse(slotOpen, sActionRes);
    }
    public CustomResponseMessage findAll() {
    	String sActionRes = "Slot open found";
    	List<SlotOpen> slotOpens = null;
    	try{
    		slotOpens = this.slotOpenRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		slotOpens = null;
    		sActionRes = "Unable to find Slot open";
    	}
        return CommonUtils.buildResponse(slotOpens, sActionRes);
    }
    public CustomResponseMessage findAsParam(SlotOpen  slotOpen) {
    	String sActionRes = "Slot open found";
    	//deptSup, dugc, senate
    	List<SlotOpen> slotOpens = null;
    	try{
    		slotOpens = this.slotOpenRepository.findAsParam(slotOpen);
    	}catch(Exception ex){
    		log.error("",ex);
    		slotOpens = null;
    		sActionRes = "Unable to find Slot open";
    	}
        return CommonUtils.buildResponse(slotOpens, sActionRes);
    }
    public CustomResponseMessage addOrUpdate(SlotOpen slotOpen){
    	String sActionRes = "save";
    	try{
        	if(slotOpen.getId() != null && slotOpen.getId() > 0){
        		sActionRes = "update";
        		SlotOpen slotOpenEx = this.slotOpenRepository.findById(slotOpen.getId()).orElse(null);
        		if(slotOpenEx != null) {
        			CommonUtils.copyNonNullProperties(slotOpen, slotOpenEx);
        			slotOpen = slotOpenEx;
        		}
        	}
        	slotOpenRepository.save(slotOpen);
    		sActionRes = "Slot open " + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		slotOpen = null;
    		sActionRes = "Unable to "+ sActionRes +" Slot open";
    	}
    	return CommonUtils.buildResponse(slotOpen, sActionRes);
    }

}
