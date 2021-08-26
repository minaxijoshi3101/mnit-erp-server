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
import com.mnit.erp.academic.course.model.CourseRegistration;
import com.mnit.erp.academic.course.service.CourseRegistrationService;
import com.mnit.erp.response.CustomResponseMessage;

@RestController
@RequestMapping("/courseregistration")
public class CourseRegistrationController extends AbstractController {
	@Autowired
	CourseRegistrationService courseRegistrationService;
	
	//Course related API's.
    @GetMapping("/{id}")
    public CustomResponseMessage courseFindById(@PathVariable Long id){
        return courseRegistrationService.courseFindById(id);
    }
    @GetMapping("/findAll")
    public CustomResponseMessage courseFindAll(){
        return courseRegistrationService.courseFindAll();
    }
    
    @PostMapping("/findAsParam")
    public CustomResponseMessage courseFindAll(@RequestBody CourseRegistration  courseRegistration){
        return courseRegistrationService.findAsParam(courseRegistration);
    }
    
    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody CourseRegistration course){
        return courseRegistrationService.addOrUpdate(course);
    }
    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody CourseRegistration course){
        return courseRegistrationService.addOrUpdate(course);
    }
    @PostMapping("/upload")
    public CustomResponseMessage uploadFile(@RequestParam("Id") Long Id,@RequestParam("fileType") String fileType, @RequestParam("file") MultipartFile file ) {
        return courseRegistrationService.uploadFile(Id, fileType, file);
    }
}
