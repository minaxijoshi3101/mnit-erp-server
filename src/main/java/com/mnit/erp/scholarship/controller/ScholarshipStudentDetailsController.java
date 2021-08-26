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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mnit.erp.AbstractController;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.scholarship.model.ApplyType;
import com.mnit.erp.scholarship.model.SanctionType;
import com.mnit.erp.scholarship.model.ScholarshipStudentDetails;
import com.mnit.erp.scholarship.service.ScholarshipStudentDetailsService;
import com.mnit.erp.util.ResponseMessageType;

@RestController
@RequestMapping("/scholarshipDetails")
public class ScholarshipStudentDetailsController extends AbstractController {
	
	@Autowired
	ScholarshipStudentDetailsService scholarshipStudentDetailsService;
	
	@GetMapping
    public CustomResponseMessage findAll(){
	List<ScholarshipStudentDetails> scholarshipStudentDetails = this.scholarshipStudentDetailsService.findAll();
	 ResponseMessageType responseMessageType = Objects.nonNull(scholarshipStudentDetails) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
     return CustomResponseMessage.builder().message(Objects.nonNull(scholarshipStudentDetails) && !scholarshipStudentDetails.isEmpty() ? "Scholarship Student Details found successfully in masters!" : "Unable to find Scholarship Student Details in masters!")
             .messageType(responseMessageType)
             .response(scholarshipStudentDetails).build();
	}
	
	@PostMapping("/add")
    public CustomResponseMessage add(@RequestBody  ScholarshipStudentDetails scholarshipStudentDetails){
		ScholarshipStudentDetails scholarshipStudentDetails1 = this.scholarshipStudentDetailsService.add(scholarshipStudentDetails);
        ResponseMessageType responseMessageType = Objects.nonNull(scholarshipStudentDetails1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(scholarshipStudentDetails1) ? "scholarshipStudentDetails added successfully in masters!" : "Unable to add scholarshipStudentDetails1 in masters!")
                .messageType(responseMessageType)
                .response(scholarshipStudentDetails1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody ScholarshipStudentDetails scholarshipStudentDetails){
    	ScholarshipStudentDetails scholarshipStudentDetails1 = this.scholarshipStudentDetailsService.update(scholarshipStudentDetails);
        ResponseMessageType responseMessageType = Objects.nonNull(scholarshipStudentDetails1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(scholarshipStudentDetails1) ? "scholarshipStudentDetails updated successfully in masters!" : "Unable to update scholarshipStudentDetails in masters!")
                .messageType(responseMessageType)
                .response(scholarshipStudentDetails1).build();
    }
	
    @GetMapping("/findById/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
    	ScholarshipStudentDetails scholarshipStudentDetails1 = this.scholarshipStudentDetailsService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(scholarshipStudentDetails1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(scholarshipStudentDetails1) ? "scholarshipStudentDetails found successfully in masters!" : "Unable to find scholarshipStudentDetails in masters!")
                .messageType(responseMessageType)
                .response(scholarshipStudentDetails1).build();
    }
    
    @GetMapping("/findByStudentId/{studentId}")
    public List<ScholarshipStudentDetails> findAllByStudentId(@PathVariable Long studentId){
        return this.scholarshipStudentDetailsService.findByStudentId(studentId);
    }
    
    @GetMapping("/findByScholarshipMasterId/{scholarshipMasterId}")
    public List<ScholarshipStudentDetails> findAllByScholarshipMasterId(@PathVariable Long scholarshipMasterId){
        return this.scholarshipStudentDetailsService.findAllByScholarshipMasterId(scholarshipMasterId);
    }
    
    @GetMapping("/findByApplyType/{applyType}")
    public List<ScholarshipStudentDetails> findbyApplicationType(@PathVariable("applyType")  ApplyType applyType){
        return this.scholarshipStudentDetailsService.findAllByApplicationType(applyType);
    }
    
    @PostMapping("/upload")
    public CustomResponseMessage uploadFile(@RequestParam("Id") Long Id,@RequestParam("fileType") String fileType, @RequestParam("file") MultipartFile file ) {
        return scholarshipStudentDetailsService.uploadFile(Id, fileType, file);
    }
    
    @GetMapping("/findByScholarshipYear/{year}")
    public List<ScholarshipStudentDetails> findAllByScholarshipYear(@PathVariable Long year){
    return this.scholarshipStudentDetailsService.findByScholarshipYear(year);
    }
    
    @GetMapping("/findBySanctionYear/{year}")
    public List<ScholarshipStudentDetails> findAllBySanctionYear(@PathVariable Long year){
        return this.scholarshipStudentDetailsService.findBySanctionYear(year);
        }
    @GetMapping("/findBySanctionType/{sanctionType}")
    public List<ScholarshipStudentDetails> findAllBySanctionType(@PathVariable("sanctionType") SanctionType sanctionType){
    	return this.scholarshipStudentDetailsService.findBySanctionType(sanctionType);
    }
    
    @GetMapping("/findByFinancialYear/{year}")
    public List<ScholarshipStudentDetails> findAllByFinancialYear(@PathVariable Long year){
    	return this.scholarshipStudentDetailsService.findByFinancialYear(year);
    }
    
}
