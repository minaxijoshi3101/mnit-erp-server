package com.mnit.erp.finance.claimBehaviourSetting.service;

import com.mnit.erp.finance.claimBehaviourSetting.model.ClaimBehaviourSetting;

import java.util.List;

/**
 * Contains Claim behaviour setting Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 16 July, 2021
 */
public interface ClaimBehaviourSettingService {
    ClaimBehaviourSetting add(ClaimBehaviourSetting claimBehaviourSetting);
    ClaimBehaviourSetting update(ClaimBehaviourSetting claimBehaviourSetting);
    ClaimBehaviourSetting find(Long claimSubTypeId);
    List<ClaimBehaviourSetting> findAll();
}
