package com.mnit.erp.academic.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.course.model.CourseExtraCredit;
import com.mnit.erp.academic.course.service.CourseExtraCreditService;
import com.mnit.erp.response.CustomResponseMessage;

@RestController
@RequestMapping("/courseextracredit")
public class CourseExtraCreditController extends AbstractController {
	@Autowired
	CourseExtraCreditService courseExtraCreditService;
	
	//Course related API's.
    @GetMapping("/{id}")
    public CustomResponseMessage courseFindById(@PathVariable Long id){
        return courseExtraCreditService.courseFindById(id);
    }
    @GetMapping("/findAll")
    public CustomResponseMessage courseFindAll(){
        return courseExtraCreditService.courseFindAll();
    }
    
    @PostMapping("/findAsParam")
    public CustomResponseMessage courseFindAll(@RequestBody CourseExtraCredit  courseExtraCredit){
        return courseExtraCreditService.courseFindAll(courseExtraCredit);
    }
    
    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody CourseExtraCredit course){
        return courseExtraCreditService.addOrUpdate(course);
    }
    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody CourseExtraCredit course){
        return courseExtraCreditService.addOrUpdate(course);
    }
}
