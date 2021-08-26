package com.mnit.erp.academic.fee.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mnit.erp.academic.fee.model.*;
import com.mnit.erp.academic.fee.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class FeeService{
    @Autowired
    FeeHeadRepository feeHeadRepository;
    @Autowired
    FeeSubHeadRepository feeSubHeadRepository;

    @Autowired
    FeeStructureRepository feeStructureRepository;
    @Autowired
    FeeStructureDetRepository feeStructureDetRepository;

    @Autowired
	FeePaymentRepository feePaymentRepository;
    
    public CustomResponseMessage headFind(Long id) {
    	String sActionRes = "Fee head found";
    	FeeHead feeHead = null;
    	try{
            feeHead = this.feeHeadRepository.findById(id).orElse(null);
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find fee head";
    	}
        return CommonUtils.buildResponse(feeHead, sActionRes);
    }
    public CustomResponseMessage headFindAll() {
    	String sActionRes = "Fee head found";
    	List<FeeHead> feeHeads = null;
    	try{
            feeHeads = this.feeHeadRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find fee head";
    	}
        return CommonUtils.buildResponse(feeHeads, sActionRes);
    }
    public CustomResponseMessage addOrUpdate(FeeHead feeHead){
    	String sActionRes = "save";
    	try{
        	if(feeHead.getId() != null && feeHead.getId() > 0){
        		sActionRes = "update";
        		FeeHead feeHeadEx = feeHeadRepository.findById(feeHead.getId()).orElse(null);
        		if(feeHeadEx != null) 
        			feeHeadEx.updateJson(feeHead);
         	}
    		feeHeadRepository.save(feeHead);
    		sActionRes = "Fee head " + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to "+ sActionRes +" fee head ";
    	}
    	return CommonUtils.buildResponse(feeHead, sActionRes);
    }

    public CustomResponseMessage subHeadFind(Long id){
    	String sActionRes = "Fee sub head found";
    	FeeSubHead feeSubHead = null;
    	try{
            feeSubHead = feeSubHeadRepository.findById(id).orElse(null);
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find fee sub head";
    	}
        return CommonUtils.buildResponse(feeSubHead, sActionRes);
    }
    public CustomResponseMessage subHeadAsHeadId(Long headId){
    	String sActionRes = "Fee sub head found";
    	List<FeeSubHead> feeSubHeads = null;
    	try{
            feeSubHeads = this.feeSubHeadRepository.findByFeeheadId(headId);
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find fee sub head";
    	}
        return CommonUtils.buildResponse(feeSubHeads, sActionRes);
    }
    public CustomResponseMessage subHeadFindAll(){
    	String sActionRes = "Fee sub head found";
    	List<FeeSubHead> feeSubHeads = null;
    	try{
            feeSubHeads = this.feeSubHeadRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find fee sub head";
    	}
        return CommonUtils.buildResponse(feeSubHeads, sActionRes);
    }
    public CustomResponseMessage addOrUpdate(FeeSubHead feeSubHead){
    	String sActionRes = "save";
    	try{
        	if(feeSubHead.getId() != null && feeSubHead.getId() > 0){
        		sActionRes = "update";
        		FeeSubHead feeSubHeadEx = this.feeSubHeadRepository.findById(feeSubHead.getId()).orElse(null);
        		if(feeSubHeadEx != null) {
        			feeSubHeadEx.updateJson(feeSubHead);
        		}
        	}
        	feeSubHeadRepository.save(feeSubHead);
    		sActionRes = "Fee sub head " + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to "+ sActionRes +" fee sub head ";
    	}
    	return CommonUtils.buildResponse(feeSubHead,sActionRes);
    }
    
    public CustomResponseMessage feeStructureFind(Long id){
    	String sActionRes = "Fee structure found";
    	FeeStructure feeStructure = null;
    	try{
            feeStructure = this.feeStructureRepository.findById(id).orElse(null);
            List<FeeStructureDet> feeStructureDet = this.feeStructureDetRepository.findByFeeStructure(feeStructure); 
            feeStructure.setFeeStructureDet(feeStructureDet);
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find fee structure";
    	}
        return CommonUtils.buildResponse(feeStructure, sActionRes);
    }
    public CustomResponseMessage feeStructureFindAll(){
    	String sActionRes = "Fee structure found";
    	List<FeeStructure> feeStructures = null;
        try{
        	feeStructures = this.feeStructureRepository.findAll();
        	for( FeeStructure feeStructure : feeStructures){
	            List<FeeStructureDet> feeStructureDet = this.feeStructureDetRepository.findByFeeStructure(feeStructure); 
	            feeStructure.setFeeStructureDet(feeStructureDet);
	        }
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find fee structure";
    	}
        return CommonUtils.buildResponse(feeStructures, sActionRes);
    }
    public FeeStructure findStructure(FeeStructure feeStructure){
    	FeeStructure feeStructureEx = null;
    	try{
            if( feeStructure.getAcYear() == null || feeStructure.getFeeMasterType() == null || feeStructure.getSemester() == null ||
	            feeStructure.getStudentType() == null || feeStructure.getDegree() == null || feeStructure.getAdmissionType() == null || 
	            feeStructure.getCategory() == null || feeStructure.getFamilyIncomeCategory() == null || feeStructure.getCurrency() == null || 
	            feeStructure.getStatus() == null){
            	feeStructureEx = new FeeStructure();
            	feeStructureEx.setStatus("Error : invalid values");            	
            }else{
            	// Degree
            	for(String degree : feeStructure.getDegree().split("\\,")){
            		degree = degree + ",";
            		String admissionType = feeStructure.getAdmissionType().split("\\,")[0];
            		String category = feeStructure.getCategory().split("\\,")[0];
            		feeStructureEx = feeStructureRepository.getExistingStructure(feeStructure.getFeeMasterType(),feeStructure.getAcYear(),feeStructure.getSemester(),
            				feeStructure.getStudentType(),degree,admissionType,category,feeStructure.getFamilyIncomeCategoryId(),feeStructure.getCurrency(),feeStructure.getStatus());
            		if(feeStructureEx != null) break;
            	}
            	if(feeStructureEx == null){
                	//AdmissionType
                	for(String admissionType : feeStructure.getAdmissionType().split("\\,")){
                		admissionType = admissionType + ",";
                		String degree = feeStructure.getDegree().split("\\,")[0] + ",";
                		String category = feeStructure.getCategory().split("\\,")[0];
                		feeStructureEx = feeStructureRepository.getExistingStructure(feeStructure.getFeeMasterType(),feeStructure.getAcYear(),feeStructure.getSemester(),
                				feeStructure.getStudentType(),degree,admissionType,category,feeStructure.getFamilyIncomeCategoryId(),feeStructure.getCurrency(),feeStructure.getStatus());
                		if(feeStructureEx != null) break;
                	}
            	}
            	//Category
            	if(feeStructureEx == null){
                	for(String category : feeStructure.getCategory().split("\\,")){
                		category = category + ",";
                		String degree = feeStructure.getDegree().split("\\,")[0];
                		String admissionType = feeStructure.getAdmissionType().split("\\,")[0];
                		feeStructureEx = feeStructureRepository.getExistingStructure(feeStructure.getFeeMasterType(),feeStructure.getAcYear(),feeStructure.getSemester(),
                				feeStructure.getStudentType(),degree,admissionType,category,feeStructure.getFamilyIncomeCategoryId(),feeStructure.getCurrency(),feeStructure.getStatus());
                		if(feeStructureEx != null) break;
                	}
            	}
            	if(feeStructureEx != null && !feeStructureEx.getStatus().contains("Error")){
            		List<FeeStructureDet> feeStrDetList = this.feeStructureDetRepository.findByFeeStructureAndStatus(feeStructureEx,"Active");
            		feeStructureEx.setFeeStructureDet(feeStrDetList);
            	}
            }
    	}catch(Exception ex){
    		log.error("",ex);
    	}
    	return feeStructureEx;
    }
    
    public CustomResponseMessage addOrUpdate(FeeStructure feeStructure){
    	String sActionRes = "save";
    	try{
            if( feeStructure.getAcYear() == null || feeStructure.getFeeMasterType() == null || feeStructure.getSemester() == null ||
    	            feeStructure.getStudentType() == null || feeStructure.getDegree() == null || feeStructure.getAdmissionType() == null || 
    	            feeStructure.getCategory() == null || feeStructure.getFamilyIncomeCategory() == null || feeStructure.getCurrency() == null || 
    	            feeStructure.getStatus() == null){
            	sActionRes = "Invalid values";
    			feeStructure = null;
            }    		
            if(sActionRes.equals("save")){
            	if(feeStructure.getId() != null && feeStructure.getId() > 0){
            		sActionRes = "update";
            		FeeStructure feeStructEx = this.feeStructureRepository.findById(feeStructure.getId()).orElse(null);
            		if(feeStructEx != null) feeStructEx.updateJson(feeStructure);
            		List<FeeStructureDet> feeStrDetList = this.feeStructureDetRepository.findByFeeStructureAndStatus(feeStructure,"Active");;
            		for(FeeStructureDet feeDetItem : feeStrDetList) feeDetItem.setStatus("InActive");
            		feeStructureDetRepository.saveAll(feeStrDetList);
            	}else{ // check for duplicate feeStructure
            		FeeStructure feeStructureEx =  findStructure(feeStructure);
            		if(feeStructureEx != null){
            			if(feeStructureEx.getStatus() == null || !feeStructureEx.getStatus().contains("Error")){
                			sActionRes = "Duplicate structure not allowed";
                			feeStructure = null;
            			}
            		}
            	}
            	if(!sActionRes.contains("Duplicate")){
                	feeStructureRepository.save(feeStructure);
            		if(feeStructure.getFeeStructureDet() != null){
            			for(FeeStructureDet feeDetItem : feeStructure.getFeeStructureDet()) 
            				feeDetItem.setFeeStructure(feeStructure);
                    	feeStructureDetRepository.saveAll(feeStructure.getFeeStructureDet());
            		}
            		sActionRes = "Fee structure " + sActionRes + " successfuly";
            	}
            }
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to "+ sActionRes +" fee structure";
    	}
    	return CommonUtils.buildResponse(feeStructure, sActionRes);
    }

    public CustomResponseMessage feeStructureDetFind(Long id){
    	String sActionRes = "Fee structure detail found";
    	FeeStructureDet feeStructureDet = null;
    	try{
            feeStructureDet = this.feeStructureDetRepository.findById(id).orElse(null);
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find fee structure detail";
    	}
        return CommonUtils.buildResponse(feeStructureDet, sActionRes);
    }
    public CustomResponseMessage feeStructureDetFindAll(){
    	String sActionRes = "Fee structure detail found";
    	List<FeeStructureDet> feeStructureDets = null;
    	try{
    		feeStructureDets = this.feeStructureDetRepository.findAll();
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find fee structure detail";
    	}
        return CommonUtils.buildResponse(feeStructureDets, sActionRes);
    }
    public CustomResponseMessage findAsStructureId(Long structureId){
    	String sActionRes = "Fee structure detail found";
    	List<FeeStructureDet> feeStructureDets = null;
    	try{
    		feeStructureDets = this.feeStructureDetRepository.findByFeeStructure_Id(structureId);
    	}catch(Exception ex){
    		log.error("",ex);
    		sActionRes = "Unable to find fee structure detail";
    	}
        return CommonUtils.buildResponse(feeStructureDets, sActionRes);
    }

    public CustomResponseMessage feeOnlinePay() {
    	String sActionRes = "Success";
    	Map<String,Object> returnDummy = new HashMap<String, Object>();
    	returnDummy.put("studentId", 31);
    	returnDummy.put("trId", "TR8596748521");
    	returnDummy.put("trDate", "19/08/2021");
    	returnDummy.put("trAount", 18975.85);
        return CommonUtils.buildResponse(returnDummy, sActionRes);
    }

	public StudentFee saveStudentFeeStructure(long structureId, long studentId) {
    	List<FeeStructureDet> feeStructureDetList = this.feeStructureDetRepository.findByFeeStructure_Id(structureId);

		StudentFee studentFee = new StudentFee();
		studentFee.setStudent_id(studentId);
		studentFee.setFeestructure_id(studentId);
		double totalAmount = 0;
		List<StudentFeeStructure> studentFeeStructureList = new ArrayList<>();

    	for (FeeStructureDet feeStructureDet : feeStructureDetList) {
    		StudentFeeStructure studentFeeStructure = new StudentFeeStructure();
    		totalAmount = totalAmount + feeStructureDet.getFeeAmount();
    		studentFeeStructure.setFeehead_id(feeStructureDet.getFeeHeadId());
    		studentFeeStructure.setFeesubhead_id(feeStructureDet.getFeeSubHeadId());
    		studentFeeStructure.setFeeamount((double)feeStructureDet.getFeeAmount());

    		studentFeeStructureList.add(studentFeeStructure);
		}

    	studentFee.setTotal_amount(totalAmount);
		studentFee.setStudentFeeStructures(studentFeeStructureList);

		return this.feePaymentRepository.save(studentFee);
	}

}
