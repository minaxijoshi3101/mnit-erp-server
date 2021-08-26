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
import com.mnit.erp.academic.course.model.CourseScheme;
import com.mnit.erp.academic.course.service.CourseSchemeService;
import com.mnit.erp.response.CustomResponseMessage;

@RestController
@RequestMapping("/coursescheme")
public class CourseSchemeController extends AbstractController {
	@Autowired
	CourseSchemeService courseSchemeService;
	
	//Course related API's.
    @GetMapping("/{id}")
    public CustomResponseMessage courseFindById(@PathVariable Long id){
        return courseSchemeService.courseFindById(id);
    }
    @GetMapping("/findAll")
    public CustomResponseMessage courseFindAll(){
        return courseSchemeService.courseFindAll();
    }
    
    @PostMapping("/findAsParam")
    public CustomResponseMessage courseFindAll(@RequestBody CourseScheme  courseScheme){
        return courseSchemeService.courseFindAll(courseScheme);
    }
    
    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody CourseScheme courseScheme){
        return courseSchemeService.addOrUpdate(courseScheme);
    }
    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody CourseScheme courseScheme){
        return courseSchemeService.addOrUpdate(courseScheme);
    }
}
