package com.mnit.erp.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.academic.course.model.CourseExtraCredit;
import com.mnit.erp.hr.model.Kra;
import com.mnit.erp.hr.repository.KraGroupRepository;
import com.mnit.erp.hr.repository.KraRepository;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KraService {

    @Autowired
    KraRepository kraRepository;

    @Autowired
    KraGroupRepository kraGroupRepositoryObj;

    public CustomResponseMessage findById(Long id) {
    	String sActionRes = "KRA found by id";
    	Kra kra = null;
    	try{
            kra = kraRepository.findById(id).orElse(null);           
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find KRA by id";
    	}
        return CommonUtils.buildResponse(kra, sActionRes);
    }
    public CustomResponseMessage findAll() {
    	String sActionRes = "KRA found";
    	List<Kra> kras = null;
    	try{
    		kras = kraRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find Kra";
    	}
        return CommonUtils.buildResponse(kras, sActionRes);
    }
    public CustomResponseMessage addOrUpdate(Kra kra){
    	String sActionRes = "save";
    	try{
        	if(kra.getId() != null && kra.getId() > 0){
        		sActionRes = "update";
        		Kra kraEx = kraRepository.findById(kra.getId()).orElse(null);
        		if(kraEx != null) {
        			CommonUtils.copyNonNullProperties(kra, kraEx);
        			kra = kraEx;
        		}
        	}
        	kraRepository.save(kra);
    		sActionRes = "Kra " + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		kra = null;
    		sActionRes = "Unable to "+ sActionRes +" Kra";
    	}
    	return CommonUtils.buildResponse(kra, sActionRes);
    }
}
