package com.mnit.erp.academic.fee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.fee.model.FeeSubHead;

@Repository
public interface FeeSubHeadRepository extends JpaRepository<FeeSubHead, Long> {
	List<FeeSubHead> findByFeeheadId(Long headId);
}
