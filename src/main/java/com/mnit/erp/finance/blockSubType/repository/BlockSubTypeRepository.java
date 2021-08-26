package com.mnit.erp.finance.blockSubType.repository;

import com.mnit.erp.finance.blockSubType.model.BlockSubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contains block sub type repository
 *
 * @author: Tejpal Singh
 * @date: 19 July, 2021
 */
@Repository
public interface BlockSubTypeRepository extends JpaRepository<BlockSubType, Long> {
    BlockSubType findByClaimSubType_Id(Long blockSubTypeId);
    BlockSubType findByClaimSubType_IdAndClaimBehaviourSetting_IdAndIdNot(Long blockSubTypeId, Long claimBehaviourSettingId, Long id);
    List<BlockSubType> findByClaimBehaviourSetting_Id(Long claimBehaviourSettingId);
}
