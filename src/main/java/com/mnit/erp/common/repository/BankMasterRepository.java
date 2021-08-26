package com.mnit.erp.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.common.model.BankMaster;

@Repository
public interface BankMasterRepository extends JpaRepository<BankMaster, Long> {
	BankMaster findByIFSC(String ifsc);
}
