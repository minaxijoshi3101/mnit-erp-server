package com.mnit.erp.academic.fee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.fee.model.FeeStructure;
import com.mnit.erp.academic.fee.model.FeeStructureDet;

@Repository
public interface FeeStructureDetRepository extends JpaRepository<FeeStructureDet, Long> {
	List<FeeStructureDet> findByFeeStructureAndStatus(FeeStructure feeStructure,String status);
	List<FeeStructureDet> findByFeeStructure(FeeStructure feeStructure);
	List<FeeStructureDet> findByFeeStructure_Id(Long feeStructureId);
}
