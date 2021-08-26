package com.mnit.erp.finance.claim.service;

import com.mnit.erp.finance.claim.model.Claim;
import com.mnit.erp.finance.claim.model.ClaimBehaviour;

import java.util.List;

/**
 * Contains Claim Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 13 July, 2021
 */
public interface ClaimService {
    Claim add(Claim claim);
    Claim update(Claim claim);
    Claim find(Long claimId);
    List<Claim> findAll();
    List<Claim> findAllUnderClaimBehaviour(ClaimBehaviour claimBehaviour);
}
