package com.mnit.erp.finance.claimSubType.repository;

import com.mnit.erp.finance.claimSubType.model.ClaimSubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contains claim sub type repository
 *
 * @author: Tejpal Singh
 * @date: 13 July, 2021
 */
@Repository
public interface ClaimSubTypeRepository extends JpaRepository<ClaimSubType, Long> {
    ClaimSubType findByNameAndClaim_Id(String claimSubTypeName, Long claimId);
    ClaimSubType findByNameAndClaim_IdAndIdNot(String claimSubTypeName, Long claimId, Long Id);
    List<ClaimSubType> findByClaim_Id(Long claimId);
}
