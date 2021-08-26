package com.mnit.erp.finance.spanSubType.service;

import com.mnit.erp.finance.blockSubType.model.BlockSubType;
import com.mnit.erp.finance.spanSubType.model.SpanSubType;

import java.util.List;

/**
 * Contains Span Sub Type Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 19 July, 2021
 */
public interface SpanSubTypeService {
    SpanSubType add(SpanSubType spanSubType);
    SpanSubType update(SpanSubType spanSubType);
    SpanSubType find(Long spanSubTypeId);
    List<SpanSubType> findByClaimBehaviourSetting(Long claimBehaviourSettingId);
}
