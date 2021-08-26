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
import com.mnit.erp.academic.course.model.SemesterWithdrawal;
import com.mnit.erp.academic.course.service.SemesterWithdrawalService;
import com.mnit.erp.response.CustomResponseMessage;

@RestController
@RequestMapping("/semesterwithdrawal")
public class SemesterWithdrawalController extends AbstractController {
	@Autowired
	SemesterWithdrawalService semesterWithdrawalService;
	
	//Semester Withdrawal related API's.
    @GetMapping("/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
        return semesterWithdrawalService.findById(id);
    }
    @GetMapping("/findAll")
    public CustomResponseMessage findAll(){
        return semesterWithdrawalService.findAll();
    }
    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody SemesterWithdrawal  semesterWithdrawal){
        return semesterWithdrawalService.addOrUpdate(semesterWithdrawal);
    }
    @PostMapping("/findAsParam")
    public CustomResponseMessage findAsParam(@RequestBody SemesterWithdrawal  semesterWithdrawal){
        return semesterWithdrawalService.findAsParam(semesterWithdrawal);
    }
    
    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody SemesterWithdrawal semesterWithdrawal){
        return semesterWithdrawalService.addOrUpdate(semesterWithdrawal);
    }
    @PostMapping("/upload")
    public CustomResponseMessage uploadFile(@RequestParam("semesterWithdrawalId") Long semesterWithdrawalId,@RequestParam("fileType") String fileType, @RequestParam("file") MultipartFile file ) {
        return semesterWithdrawalService.uploadFile(semesterWithdrawalId, fileType, file);
    }
    
}
