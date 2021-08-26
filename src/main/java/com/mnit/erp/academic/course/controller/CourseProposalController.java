package com.mnit.erp.academic.course.controller;

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
import com.mnit.erp.academic.course.model.CourseProposal;
import com.mnit.erp.academic.course.service.CourseProposalService;
import com.mnit.erp.response.CustomResponseMessage;

@RestController
@RequestMapping("/courseproposal")
public class CourseProposalController extends AbstractController {
	@Autowired
	CourseProposalService courseProposalService;
	
	//Course related API's.
    @GetMapping("/{id}")
    public CustomResponseMessage courseFindById(@PathVariable Long id){
        return courseProposalService.courseFindById(id);
    }
    @GetMapping("/findAll")
    public CustomResponseMessage courseFindAll(){
        return courseProposalService.courseFindAll();
    }
    
    @PostMapping("/findAsParam")
    public CustomResponseMessage courseFindAll(@RequestBody CourseProposal  courseProposal){
        return courseProposalService.courseFindAll(courseProposal);
    }
    
    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody CourseProposal course){
        return courseProposalService.addOrUpdate(course);
    }
    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody CourseProposal course){
        return courseProposalService.addOrUpdate(course);
    }
    @PostMapping("/upload")
    public CustomResponseMessage uploadFile(@RequestParam("Id") Long Id,@RequestParam("fileType") String fileType, @RequestParam("file") MultipartFile file ) {
        return courseProposalService.uploadFile(Id, fileType, file);
    }
}
