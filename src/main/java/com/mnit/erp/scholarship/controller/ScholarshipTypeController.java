package com.mnit.erp.scholarship.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.mnit.erp.AbstractController;
import com.mnit.erp.response.CustomResponseMessage;

import com.mnit.erp.scholarship.model.ScholarshipType;
import com.mnit.erp.scholarship.service.ScholarshipTypeService;
import com.mnit.erp.util.ResponseMessageType;

@RestController
@RequestMapping("/scholarshipType")
public class ScholarshipTypeController extends AbstractController{

	@Autowired
	ScholarshipTypeService scholarshipTypeService;
	
	@GetMapping
    public CustomResponseMessage findAll(){
	List<ScholarshipType> scholarshipType = this.scholarshipTypeService.findAll();
	 ResponseMessageType responseMessageType = Objects.nonNull(scholarshipType) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
     return CustomResponseMessage.builder().message(Objects.nonNull(scholarshipType) && !scholarshipType.isEmpty() ? "Scholarship Type found successfully in masters!" : "Unable to find scholarship Type in masters!")
             .messageType(responseMessageType)
             .response(scholarshipType).build();
	}
	
	@PostMapping("/add")
    public CustomResponseMessage add(@RequestBody  ScholarshipType scholarshipType){
		ScholarshipType scholarshipType1 = this.scholarshipTypeService.add(scholarshipType);
        ResponseMessageType responseMessageType = Objects.nonNull(scholarshipType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(scholarshipType1) ? "scholarshipType added successfully in masters!" : "Unable to add scholarshipType in masters!")
                .messageType(responseMessageType)
                .response(scholarshipType1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody ScholarshipType scholarshipType){
    	ScholarshipType scholarshipType1 = this.scholarshipTypeService.update(scholarshipType);
        ResponseMessageType responseMessageType = Objects.nonNull(scholarshipType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(scholarshipType1) ? "scholarshipType updated successfully in masters!" : "Unable to update scholarshipType in masters!")
                .messageType(responseMessageType)
                .response(scholarshipType1).build();
    }
    
    
    @GetMapping("/findById/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
    	ScholarshipType scholarshipType1 = this.scholarshipTypeService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(scholarshipType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(scholarshipType1) ? "scholarshipType found successfully in masters!" : "Unable to find scholarshipType in masters!")
                .messageType(responseMessageType)
                .response(scholarshipType1).build();
    }
    
    
    @GetMapping("/findByType/{name}")
    public List<ScholarshipType> findAllByscholarshipType(@PathVariable String name){
        return this.scholarshipTypeService.findByScholarshipTypeName(name);
    }
}
	
    
	
