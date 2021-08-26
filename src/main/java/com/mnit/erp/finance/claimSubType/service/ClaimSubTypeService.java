package com.mnit.erp.finance.claimSubType.service;

import com.mnit.erp.finance.claimSubType.model.ClaimSubType;

import java.util.List;

/**
 * Contains Claim Sub Type Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 13 July, 2021
 */
public interface ClaimSubTypeService {
    ClaimSubType add(ClaimSubType claimSubType);
    ClaimSubType update(ClaimSubType claimSubType);
    ClaimSubType find(Long claimSubTypeId);
    List<ClaimSubType> findAll();
    List<ClaimSubType> findAllUnderClaim(Long claimId);
}
