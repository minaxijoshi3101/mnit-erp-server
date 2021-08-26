package com.mnit.erp.academic.fee.controller;

import com.mnit.erp.academic.fee.model.StudentFee;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mnit.erp.academic.fee.model.FeeHead;
import com.mnit.erp.academic.fee.model.FeeStructure;
import com.mnit.erp.academic.fee.model.FeeSubHead;
import com.mnit.erp.academic.fee.service.FeeService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.CommonUtils;

import java.util.Objects;


@RestController
@RequestMapping("/fee")
public class FeeController {
    @Autowired
    FeeService feeService;

    // Fee Heead related API's
    @GetMapping("/feeHead/{id}")
    public CustomResponseMessage headFind(@PathVariable Long id){
        return feeService.headFind(id);
    }
    @GetMapping("/feeHead/findAll")
    public CustomResponseMessage headFindAll(){
        return feeService.headFindAll();
    }
    
    @PostMapping("/feeHead/add")
    public CustomResponseMessage feeHeadadd(@RequestBody FeeHead feeHead){
        return feeService.addOrUpdate(feeHead);
    }

    @PutMapping("/feeHead/update")
    public CustomResponseMessage feeHeadUpdate(@RequestBody FeeHead feeHead){
        return feeService.addOrUpdate(feeHead);
    }

    // Fee Sub Heead related API's
    @GetMapping("/feeSubHead/{id}")
    public CustomResponseMessage subHeadFind(@PathVariable Long id){
        return feeService.subHeadFind(id);
    }
    @GetMapping("/feeSubHead/headId/{headid}")
    public CustomResponseMessage subHeadFindAsHeadId(@PathVariable Long headid){
        return feeService.subHeadAsHeadId(headid);
    }
    @GetMapping("/feeSubHead/findAll")
    public CustomResponseMessage subHeadFindAll(){
        return feeService.subHeadFindAll();
    }
    @PostMapping("/feeSubHead/add")
    public CustomResponseMessage feeSubHeadadd(@RequestBody FeeSubHead feeSubHead){
        return feeService.addOrUpdate(feeSubHead);
    }

    @PutMapping("/feeSubHead/update")
    public CustomResponseMessage feeSubHeadUpdate(@RequestBody FeeSubHead feeSubHead){
        return feeService.addOrUpdate(feeSubHead);
    }
    
    // Fee Structure related API's
    @GetMapping("/feeStructure/{id}")
    public CustomResponseMessage feeStructureFind(@PathVariable Long id){
        return feeService.feeStructureFind(id);
    }
    @GetMapping("/feeStructure/findAll")
    public CustomResponseMessage feeStructureFindAll(){
        return feeService.feeStructureFindAll();
    }
    @PostMapping("/feeStructure/add")
    public CustomResponseMessage add(@RequestBody FeeStructure feeStructure){
        return feeService.addOrUpdate(feeStructure);
    }
    @PutMapping("/feeStructure/update")
    public CustomResponseMessage update(@RequestBody FeeStructure feeStructure){
        return feeService.addOrUpdate(feeStructure);
    }

    @PostMapping("/feeStructure/search")
    public CustomResponseMessage search(@RequestBody FeeStructure feeStructure){
    	String sReturnMsg = "";
    	FeeStructure feeStructureEx =  feeService.findStructure(feeStructure);
    	if(feeStructureEx == null) 
    		sReturnMsg = "Not Found";
    	else if(feeStructureEx.getStatus() == null)
    		sReturnMsg = "Not Found";
    	else if(feeStructureEx.getStatus().contains("Error"))
    		sReturnMsg = feeStructureEx.getStatus();    		
    	else
    		sReturnMsg = "Fee Structure Found";
    	return CommonUtils.buildResponse(feeStructureEx, sReturnMsg);
    }

    // Fee Structure Details related API's
    @GetMapping("/feeStructureDet/{id}")
    public CustomResponseMessage feeStructureDetFind(@PathVariable Long id){
        return feeService.feeStructureDetFind(id);
    }

    @GetMapping("/feeStructureDet/findAll")
    public CustomResponseMessage feeStructureDetFindAll(){
        return feeService.feeStructureFindAll();
    }

    @GetMapping("/feeStructureDet/structureId/{structureId}")
    public CustomResponseMessage findAsStructureId(@PathVariable Long structureId){
        return feeService.findAsStructureId(structureId);
    }

    @PostMapping("/onlinePay")
    public CustomResponseMessage feeOnlinePay(){
        return feeService.feeOnlinePay();
    }
    
    @PostMapping("/feepayment")
    public CustomResponseMessage feePayment(@RequestParam Long structureId, @RequestParam Long studentId){
        StudentFee studentFee = feeService.saveStudentFeeStructure(structureId, studentId);

        ResponseMessageType responseMessageType = Objects.nonNull(studentFee) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentFee) ? "Success! Save Student Fee Structure" : "Something went wrong.")
                .messageType(responseMessageType)
                .response(studentFee).build();
    }

}
