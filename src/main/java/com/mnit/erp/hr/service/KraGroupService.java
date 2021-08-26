package com.mnit.erp.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.hr.model.KraGroup;
import com.mnit.erp.hr.repository.KraGroupRepository;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KraGroupService {

    @Autowired
    KraGroupRepository kraGroupRepository;

    public CustomResponseMessage findById(Long id) {
    	String sActionRes = "Kra Group found by id";
    	KraGroup kraGroup = null;
    	try{
            kraGroup = kraGroupRepository.findById(id).orElse(null);           
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find Kra Group by id";
    	}
        return CommonUtils.buildResponse(kraGroup, sActionRes);
    }
    public CustomResponseMessage findAll() {
    	String sActionRes = "Kra Group found";
    	List<KraGroup> kraGroups = null;
    	try{
    		kraGroups = kraGroupRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find Kra Group";
    	}
        return CommonUtils.buildResponse(kraGroups, sActionRes);
    }
    public CustomResponseMessage addOrUpdate(KraGroup kraGroup){
    	String sActionRes = "save";
    	try{
        	if(kraGroup.getId() != null && kraGroup.getId() > 0){
        		sActionRes = "update";
        		KraGroup kraGroupEx = kraGroupRepository.findById(kraGroup.getId()).orElse(null);
        		if(kraGroupEx != null) {
        			CommonUtils.copyNonNullProperties(kraGroup, kraGroupEx);
        			kraGroup = kraGroupEx;
        		}
        	}
        	kraGroupRepository.save(kraGroup);
    		sActionRes = "Kra Group " + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		kraGroup = null;
    		sActionRes = "Unable to "+ sActionRes +" Kra Group";
    	}
    	return CommonUtils.buildResponse(kraGroup, sActionRes);
    }
}
