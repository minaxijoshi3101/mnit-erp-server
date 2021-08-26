package com.mnit.erp.finance.spanSubType.repository;

import com.mnit.erp.finance.blockSubType.model.BlockSubType;
import com.mnit.erp.finance.spanSubType.model.SpanSubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contains span sub type repository
 *
 * @author: Tejpal Singh
 * @date: 19 July, 2021
 */
@Repository
public interface SpanSubTypeRepository extends JpaRepository<SpanSubType, Long> {
    SpanSubType findByClaimSubType_Id(Long spanSubTypeId);
    SpanSubType findByClaimSubType_IdAndClaimBehaviourSetting_IdAndIdNot(Long spanSubTypeId, Long claimBehaviourSettingId, Long id);
    List<SpanSubType> findByClaimBehaviourSetting_Id(Long claimBehaviourSettingId);
}
