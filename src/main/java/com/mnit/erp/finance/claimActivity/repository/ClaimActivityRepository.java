package com.mnit.erp.finance.claimActivity.repository;

import com.mnit.erp.finance.claimActivity.model.ClaimActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contains claim activity repository
 *
 * @author: Tejpal Singh
 * @date: 14 July, 2021
 */
@Repository
public interface ClaimActivityRepository extends JpaRepository<ClaimActivity, Long> {
    ClaimActivity findByNameAndClaim_IdAndClaimSubType_Id(String claimSubTypeName, Long claimId, Long claimSubTypeId);
    ClaimActivity findByNameAndClaim_IdAndClaimSubType_IdAndIdNot(String claimSubTypeName, Long claimId, Long claimSubTypeId, Long Id);
    List<ClaimActivity> findByClaim_Id(Long claimId);
}
