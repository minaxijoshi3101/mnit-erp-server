package com.mnit.erp.academic.fee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.fee.model.FeeHead;

@Repository
public interface FeeHeadRepository extends JpaRepository<FeeHead, Long> {
}
