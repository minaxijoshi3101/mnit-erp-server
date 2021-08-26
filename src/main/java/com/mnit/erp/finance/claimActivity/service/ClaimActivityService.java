package com.mnit.erp.finance.claimActivity.service;

import com.mnit.erp.finance.claimActivity.model.ClaimActivity;

import java.util.List;

/**
 * Contains Claim activity Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 14 July, 2021
 */
public interface ClaimActivityService {
    ClaimActivity add(ClaimActivity claimActivity);
    ClaimActivity update(ClaimActivity claimActivity);
    ClaimActivity find(Long claimSubTypeId);
    List<ClaimActivity> findAll();
}
