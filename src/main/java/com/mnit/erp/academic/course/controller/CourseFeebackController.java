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
import com.mnit.erp.academic.course.model.CourseFeedback;
import com.mnit.erp.academic.course.service.CourseFeedbackService;
import com.mnit.erp.response.CustomResponseMessage;

@RestController
@RequestMapping("/feedback")
public class CourseFeebackController extends AbstractController {
	@Autowired
	CourseFeedbackService courseFeedbackService;
	
	//Course related API's.
    @GetMapping("/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
        return courseFeedbackService.findById(id);
    }
    @GetMapping("/findAll")
    public CustomResponseMessage findAll(){
        return courseFeedbackService.findAll();
    }
    
    @PostMapping("/findAsParam")
    public CustomResponseMessage courseFindAll(@RequestBody CourseFeedback courseFeedback){
        return courseFeedbackService.findAsParam(courseFeedback);
    }
    
    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody CourseFeedback courseFeedback){
        return courseFeedbackService.addOrUpdate(courseFeedback);
    }
    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody CourseFeedback courseFeedback){
        return courseFeedbackService.addOrUpdate(courseFeedback);
    }
}
