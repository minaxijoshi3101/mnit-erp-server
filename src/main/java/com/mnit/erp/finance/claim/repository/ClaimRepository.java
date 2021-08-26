package com.mnit.erp.finance.claim.repository;

import com.mnit.erp.finance.claim.model.Claim;
import com.mnit.erp.finance.claim.model.ClaimBehaviour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contains Claim repository
 *
 * @author: Tejpal Singh
 * @date: 13 July, 2021
 */
@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    Claim findByNameOrAbbreviation(String claimName, String abbreviation);
    Claim findByNameAndIdNotOrAbbreviationAndIdNot(String claimName, Long Id, String abbreviation, Long Id1);
    List<Claim> findByClaimBehaviour(ClaimBehaviour claimBehaviour);
}
