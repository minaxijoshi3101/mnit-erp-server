package com.mnit.erp.academic.branch.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.branch.model.Branch;
import com.mnit.erp.academic.branch.service.BranchService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/branch")
public class BranchController extends AbstractController {

    @Autowired
    BranchService branchService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody  Branch branch){
        Branch branch1 = this.branchService.add(branch);
        ResponseMessageType responseMessageType = Objects.nonNull(branch1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(branch1) ? "Branch added successfully in masters!" : "Unable to add branch in masters!")
                .messageType(responseMessageType)
                .response(branch1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody Branch branch){
        Branch branch1 = this.branchService.update(branch);
        ResponseMessageType responseMessageType = Objects.nonNull(branch1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(branch1) ? "Branch updated successfully in masters!" : "Unable to update branch in masters!")
                .messageType(responseMessageType)
                .response(branch1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Branch> branches = this.branchService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(branches) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(branches) && !branches.isEmpty() ? "Branches found successfully in masters!" : "Unable to find branches in masters!")
                .messageType(responseMessageType)
                .response(branches).build();
    }

    @GetMapping("/findById/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
        Branch branch1 = this.branchService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(branch1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(branch1) ? "Branch found successfully in masters!" : "Unable to find branch in masters!")
                .messageType(responseMessageType)
                .response(branch1).build();
    }

    @GetMapping("/findByDegree/{degreeId}")
    public CustomResponseMessage findAllByDegreeId(@PathVariable Long degreeId) {
        List<Branch> allByDegreeId = this.branchService.findAllByDegreeId(degreeId);
        ResponseMessageType responseMessageType = Objects.nonNull(allByDegreeId) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(allByDegreeId) && allByDegreeId.size() > 0 ? "Branches found successfully under degree!" : "Unable to find branches under degree!")
                .messageType(responseMessageType)
                .response(allByDegreeId).build();
    }

    @GetMapping("/findByProgram/{programId}")
    public CustomResponseMessage findAllByProgramId(@PathVariable Long programId){
        List<Branch> allByProgramId = this.branchService.findAllByProgramId(programId);
        ResponseMessageType responseMessageType = Objects.nonNull(allByProgramId) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(allByProgramId) && allByProgramId.size() > 0 ? "Branches found successfully under program!" : "Unable to find branches under program!")
                .messageType(responseMessageType)
                .response(allByProgramId).build();
    }


    @GetMapping("/findByDepartment/{departmentId}")
    public CustomResponseMessage findAllByDepartmentId(@PathVariable Long departmentId){
        List<Branch> allByDepartmentId = this.branchService.findAllByDepartmentId(departmentId);
        ResponseMessageType responseMessageType = Objects.nonNull(allByDepartmentId) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(allByDepartmentId) && allByDepartmentId.size() > 0 ? "Branches found successfully under department!" : "Unable to find branches under department!")
                .messageType(responseMessageType)
                .response(allByDepartmentId).build();
    }

}
