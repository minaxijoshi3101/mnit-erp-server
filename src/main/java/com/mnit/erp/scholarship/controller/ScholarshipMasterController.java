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
import com.mnit.erp.scholarship.model.ScholarshipMaster;

import com.mnit.erp.scholarship.service.ScholarshipMasterService;
import com.mnit.erp.util.ResponseMessageType;

@RestController
@RequestMapping("/scholarshipMaster")
public class ScholarshipMasterController extends AbstractController{

	@Autowired
	ScholarshipMasterService scholarshipMasterService;
	
	@GetMapping
    public CustomResponseMessage findAll(){
	List<ScholarshipMaster> scholarshipMasters = this.scholarshipMasterService.findAll();
	 ResponseMessageType responseMessageType = Objects.nonNull(scholarshipMasters) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
     return CustomResponseMessage.builder().message(Objects.nonNull(scholarshipMasters) && !scholarshipMasters.isEmpty() ? "ScholarshipMasters found successfully in masters!" : "Unable to find scholarshipMasters in masters!")
             .messageType(responseMessageType)
             .response(scholarshipMasters).build();
	}
	
	@PostMapping("/add")
    public CustomResponseMessage add(@RequestBody  ScholarshipMaster scholarshipMaster){
		ScholarshipMaster scholarshipMaster1 = this.scholarshipMasterService.add(scholarshipMaster);
        ResponseMessageType responseMessageType = Objects.nonNull(scholarshipMaster1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(scholarshipMaster1) ? "scholarshipMaster added successfully in masters!" : "Unable to add scholarshipMasters in masters!")
                .messageType(responseMessageType)
                .response(scholarshipMaster1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody ScholarshipMaster scholarshipMaster){
    	ScholarshipMaster scholarshipMaster1 = this.scholarshipMasterService.update(scholarshipMaster);
        ResponseMessageType responseMessageType = Objects.nonNull(scholarshipMaster1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(scholarshipMaster1) ? "scholarshipMasters updated successfully in masters!" : "Unable to update scholarshipMasters in masters!")
                .messageType(responseMessageType)
                .response(scholarshipMaster1).build();
    }
    
    
    @GetMapping("/findById/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
    	ScholarshipMaster scholarshipMaster1 = this.scholarshipMasterService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(scholarshipMaster1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(scholarshipMaster1) ? "scholarshipMaster found successfully in masters!" : "Unable to find scholarshipMaster in masters!")
                .messageType(responseMessageType)
                .response(scholarshipMaster1).build();
    }
    
    
    @PostMapping("/upload")
    public CustomResponseMessage uploadFile(@RequestParam("Id") Long Id,@RequestParam("fileType") String fileType, @RequestParam("file") MultipartFile file ) {
        return scholarshipMasterService.uploadFile(Id, fileType, file);
    }
    
      
    @GetMapping("/findByScholarshipName/{name}")
    public List<ScholarshipMaster> findAllByscholarshipName(@PathVariable String name){
        return this.scholarshipMasterService.findByScholarshipNames(name);
    }
	
    @GetMapping("/findByScholarshipTypeId/{Id}")
    public List<ScholarshipMaster> findAllByScholarshipTypeId(@PathVariable Long Id){
        return this.scholarshipMasterService.findAllByScholarshipMasterId(Id);
    }
    
    @GetMapping("/findByProgram/{Id}")
    public List<ScholarshipMaster> findAllByProgramId(@PathVariable Long Id){
    	return this.scholarshipMasterService.findAllByProgram(Id);
    }
}
