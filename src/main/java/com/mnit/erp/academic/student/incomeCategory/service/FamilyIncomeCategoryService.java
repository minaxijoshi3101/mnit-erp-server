package com.mnit.erp.academic.student.incomeCategory.service;

import com.mnit.erp.academic.student.incomeCategory.model.FamilyIncomeCategory;
import com.mnit.erp.academic.student.incomeCategory.repository.FamilyIncomeCategoryRepository;
import com.mnit.erp.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FamilyIncomeCategoryService {

    @Autowired
    FamilyIncomeCategoryRepository familyIncomeCategoryRepository;

    public FamilyIncomeCategory find(Long id) {
        return this.familyIncomeCategoryRepository.findById(id).orElse(null);
    }

    public List<FamilyIncomeCategory> findAll() {
        return this.familyIncomeCategoryRepository.findAll();
    }

    public FamilyIncomeCategory add(FamilyIncomeCategory familyIncomeCategory) {
        FamilyIncomeCategory familyIncomeCategory1 = this.familyIncomeCategoryRepository.findByName(familyIncomeCategory.getName());
        if(Objects.nonNull(familyIncomeCategory1)){
            throw new ServiceException("Family income : " + familyIncomeCategory.getName()+ " category already exists. Can't add new!");
        }
        return this.familyIncomeCategoryRepository.save(familyIncomeCategory);
    }
}
