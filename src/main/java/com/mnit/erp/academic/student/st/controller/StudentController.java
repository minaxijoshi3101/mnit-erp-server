package com.mnit.erp.academic.student.st.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mnit.erp.academic.student.st.filter.StudentFilter.StudentFilter;
import com.mnit.erp.academic.student.st.model.Student;
import com.mnit.erp.academic.student.st.model.StudentDocument;
import com.mnit.erp.academic.student.st.service.StudentService;
import com.mnit.erp.common.model.BankDetail;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody Student student){
        Student student1 = this.studentService.add(student);
        ResponseMessageType responseMessageType = Objects.nonNull(student1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(student1) ? "Student added successfully!" : "Unable to add student!")
                .messageType(responseMessageType)
                .response(student1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody Student student){
        Student student1 = this.studentService.update(student);
        ResponseMessageType responseMessageType = Objects.nonNull(student1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(student1) ? "Student updated successfully in masters!" : "Unable to update student!")
                .messageType(responseMessageType)
                .response(student1).build();
    }

    @GetMapping("/find/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
        Student student = this.studentService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(student) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(student) ? "Student found!" : "Unable to find student!")
                .messageType(responseMessageType)
                .response(student).build();
    }

    @GetMapping("/findByStudentId/{studentId}")
    public CustomResponseMessage findByStudentId(@PathVariable String studentId){
        Student student = this.studentService.findByStudentId(studentId);
        ResponseMessageType responseMessageType = Objects.nonNull(student) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(student) ? "Student found!" : "Unable to find student!")
                .messageType(responseMessageType)
                .response(student).build();
    }

    @GetMapping("/findAllStudentIdNull")
    public CustomResponseMessage findAllStudentIdNull(){
        List<Student> student = this.studentService.findByStudentIdNull();
        ResponseMessageType responseMessageType = Objects.nonNull(student) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(student.size() > 0 ? "Student found!" : "Unable to find student!")
                .messageType(responseMessageType)
                .response(student).build();
    }

    @GetMapping("/findAllStudentIdNullView")
    public CustomResponseMessage findAllStudentIdNullView(){
        List<Object> student = this.studentService.findByStudentIdNullView();
        ResponseMessageType responseMessageType = Objects.nonNull(student) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(student.size() > 0 ? "Student found!" : "Unable to find student!")
                .messageType(responseMessageType)
                .response(student).build();
    }

    @GetMapping("/findAllStudentIdNotNull")
    public CustomResponseMessage findAllStudentIdNotNull(){
        List<Student> student = this.studentService.findByStudentIdNotNull();
        ResponseMessageType responseMessageType = Objects.nonNull(student) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(student.size() > 0 ? "Student found!" : "Unable to find student!")
                .messageType(responseMessageType)
                .response(student).build();
    }

    @GetMapping("/findByFilter")
    public CustomResponseMessage findByFilter(@ModelAttribute StudentFilter studentFilter, Pageable pageable){
        Page<Student> page = this.studentService.findByFilter(studentFilter, pageable);
        ResponseMessageType responseMessageType = Objects.nonNull(page) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(page) && page.getTotalElements() > 0 ? "Students found!" : "Unable to find students!")
                .messageType(responseMessageType)
                .response(page).build();
    }
    
    @PostMapping("/findAsParam")
    public CustomResponseMessage findAsParam(@RequestBody Student  student){
        List<Student> students = this.studentService.findAsParam(student);
        ResponseMessageType responseMessageType = Objects.nonNull(students) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(students) && students.size() > 0 ? "Students found!" : "Unable to find students!")
                .messageType(responseMessageType)
                .response(students).build();
    }

    @PostMapping("/studentDocument/addUpdate")
    public CustomResponseMessage addUpdate(@RequestBody StudentDocument  studentDocument){
        return studentService.addUpdate(studentDocument);
    }

    @PostMapping("/studentDocument/upload")
    public CustomResponseMessage uploadFile(@RequestParam("studentDocumentId") Long Id, @RequestParam("file") MultipartFile file ) {
        return studentService.uploadFile(Id, file);
    }

    @GetMapping("/studentDocument/findAll")
    public CustomResponseMessage studentDocumentFindAll(){
        return studentService.studentDocumentFindAll();
    }

    @GetMapping("/studentDocument/findByStudentId/{studentId}")
    public CustomResponseMessage findByStudentId(@PathVariable Long studentId){
        return studentService.studentDocumentFindByStudentId(studentId,"");
    }

    @GetMapping("/studentDocument/findByPreStudentId/{studentId}")
    public CustomResponseMessage findByPreStudentId(@PathVariable Long studentId){
        return studentService.studentDocumentFindByStudentId(studentId,"Pre");
    }
    
    @RequestMapping(value="/updateApprovalStatus", method = RequestMethod.PUT, consumes = {"application/json" })
    public CustomResponseMessage updateApprovalStatus(@RequestBody Map<String, Object> reqParam){
        return studentService.updateApprovalStatus(reqParam);
    }
    
    @PutMapping("/updateBankDetail")
    public CustomResponseMessage updateBankDetail(@RequestBody BankDetail bankDetail){
        return studentService.updateBankDetail(bankDetail);
    }

    @PostMapping("/photoUpload")
    public CustomResponseMessage photoUpload(@RequestParam("preAdmissionId") Long Id, @RequestParam("photo") MultipartFile photo, @RequestParam("photoType") String photoType ) {
        return studentService.uploadPhoto(Id, photo, photoType);
    }
}
