package com.mnit.erp.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.common.model.BankDetail;

@Repository
public interface BankDetailRepository extends JpaRepository<BankDetail, Long> {
}
