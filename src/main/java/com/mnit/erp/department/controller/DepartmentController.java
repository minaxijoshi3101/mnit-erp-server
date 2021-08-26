package com.mnit.erp.department.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.department.model.Department;
import com.mnit.erp.department.service.DepartmentService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/department")
public class DepartmentController extends AbstractController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public CustomResponseMessage add(@RequestBody Department department){
        Department department1 = this.departmentService.add(department);
        ResponseMessageType responseMessageType = Objects.nonNull(department1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(department1) ? "Department added successfully in masters!" : "Unable to add department in masters!")
                .messageType(responseMessageType)
                .response(department1).build();
    }

    @PutMapping
    public CustomResponseMessage update(@RequestBody Department department){
        Department department1 = this.departmentService.update(department);
        ResponseMessageType responseMessageType = Objects.nonNull(department1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(department1) ? "Department updated successfully in masters!" : "Unable to update department in masters!")
                .messageType(responseMessageType)
                .response(department1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
        Department department1 = this.departmentService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(department1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(department1) ? "Department found successfully in masters!" : "Unable to find department in masters!")
                .messageType(responseMessageType)
                .response(department1).build();
    }

    @GetMapping
    CustomResponseMessage findAll(){
        List<Department> departments = this.departmentService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(departments) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(departments) ? "Departments loaded successfully from masters!" : "Unable to find departments in masters!")
                .messageType(responseMessageType)
                .response(departments).build();
    }
}
