package com.mnit.erp.academic.student.incomeCategory.controller;

import com.mnit.erp.academic.student.incomeCategory.model.FamilyIncomeCategory;
import com.mnit.erp.academic.student.incomeCategory.service.FamilyIncomeCategoryService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/familyIncomeCategory")
public class FamilyIncomeCategoryController {

    @Autowired
    FamilyIncomeCategoryService familyIncomeCategoryService;

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        FamilyIncomeCategory category = this.familyIncomeCategoryService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(category) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(category) ? "Family Income Category found in masters!" : "Unable to find Family Income Category in masters!")
                .messageType(responseMessageType)
                .response(category).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<FamilyIncomeCategory> categories = this.familyIncomeCategoryService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(categories) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(categories) && categories.size() > 0 ? "Family Income Categories found in masters!" : "Unable to find Family Income Categories in masters!")
                .messageType(responseMessageType)
                .response(categories).build();
    }

}
