package com.mnit.erp.academic.fee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.fee.model.FeeStructure;

@Repository
public interface FeeStructureRepository extends JpaRepository<FeeStructure, Long> {
	@Query(" SELECT FS FROM FeeStructure FS "
			+  "WHERE feeMasterType = ?1 And acYear = ?2 And semester = ?3 And studentType = ?4 And "
			+  "	  LOCATE(?5,CONCAT(degree,',')) > 0 And LOCATE(?6,CONCAT(admissionType,',')) > 0 And "
			+ "		  LOCATE(?7,CONCAT(category,',')) > 0 And familyIncomeCategory.id =?8 And currency = ?9 And "
			+  "	  status = ?10 ")
	FeeStructure getExistingStructure(String feeMasterType,String acYear,String semester, String studentType,String degree, String admissionType,String category,Long familyIncomeCategoryId, String currency, String status);
}
