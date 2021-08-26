package com.mnit.erp.finance.blockSubType.service;

import com.mnit.erp.finance.blockSubType.model.BlockSubType;

import java.util.List;

/**
 * Contains Block Sub Type Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 19 July, 2021
 */
public interface BlockSubTypeService {
    BlockSubType add(BlockSubType blockSubType);
    BlockSubType update(BlockSubType blockSubType);
    BlockSubType find(Long blockSubTypeId);
    List<BlockSubType> findByClaimBehaviourSetting(Long claimBehaviourSettingId);
}
