package com.mnit.erp.academic.student.incomeCategory.repository;

import com.mnit.erp.academic.student.incomeCategory.model.FamilyIncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyIncomeCategoryRepository extends JpaRepository<FamilyIncomeCategory, Long> {
    FamilyIncomeCategory findByName(String name);
}
