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
import com.mnit.erp.academic.course.model.Course;
import com.mnit.erp.academic.course.model.CourseType;
import com.mnit.erp.academic.course.model.ElectiveType;
import com.mnit.erp.academic.course.service.CourseService;
import com.mnit.erp.response.CustomResponseMessage;

@RestController
@RequestMapping("/course")
public class CourseController extends AbstractController {
	@Autowired
	CourseService courseService;
	
	//Course related API's.
    @GetMapping("/{id}")
    public CustomResponseMessage courseFindById(@PathVariable Long id){
        return courseService.courseFindById(id);
    }
    @GetMapping("/findAll")
    public CustomResponseMessage courseFindAll(){
        return courseService.courseFindAll();
    }
    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody Course  course){
        return courseService.addOrUpdate(course);
    }
    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody Course  course){
        return courseService.addOrUpdate(course);
    }
    //CourseType related API's
    @GetMapping("/courseType/{id}")
    public CustomResponseMessage courseTypeFindById(@PathVariable Long id){
        return courseService.courseTypeFindById(id);
    }
    @GetMapping("/courseType/findAll")
    public CustomResponseMessage courseTypeFindAll(){
        return courseService.courseTypeFindAll();
    }
    @PostMapping("/courseType")
    public CustomResponseMessage add(@RequestBody CourseType courseType){
        return courseService.addOrUpdate(courseType);
    }
    @PutMapping("/courseType")
    public CustomResponseMessage update(@RequestBody CourseType courseType){
        return courseService.addOrUpdate(courseType);
    }
    
    //ElectiveType related API's
    @GetMapping("/electiveType/{id}")
    public CustomResponseMessage ElectiveTypeFindById(@PathVariable Long id){
        return courseService.electiveTypeFindById(id);
    }
    @GetMapping("/electiveType/findAll")
    public CustomResponseMessage electiveTypeFindAll(){
        return courseService.electiveTypeFindAll();
    }
    @PostMapping("/electiveType")
    public CustomResponseMessage add(@RequestBody ElectiveType electiveType){
        return courseService.addOrUpdate(electiveType);
    }
    @PutMapping("/electiveType")
    public CustomResponseMessage update(@RequestBody ElectiveType electiveType){
        return courseService.addOrUpdate(electiveType);
    }
    
    @PostMapping("/upload")
    public CustomResponseMessage uploadFile(@RequestParam("courseId") Long courseId,@RequestParam("fileType") String fileType, @RequestParam("file") MultipartFile file ) {
        return courseService.uploadFile(courseId, fileType, file);
    }
    
}
