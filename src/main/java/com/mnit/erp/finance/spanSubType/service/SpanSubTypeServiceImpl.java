package com.mnit.erp.finance.spanSubType.service;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.finance.blockSubType.model.BlockSubType;
import com.mnit.erp.finance.spanSubType.model.SpanSubType;
import com.mnit.erp.finance.spanSubType.repository.SpanSubTypeRepository;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains Span Sub Type Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 19 July, 2021
 */
@Service
public class SpanSubTypeServiceImpl implements SpanSubTypeService {

    @Autowired
    SpanSubTypeRepository spanSubTypeRepository;

    @Override
    public SpanSubType add(SpanSubType spanSubType) {
        SpanSubType byName = spanSubTypeRepository.findByClaimSubType_Id(spanSubType.getClaimSubTypeId());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Span sub type already exists. Can't add again!");
        }

        if(this.validate(spanSubType))
            return this.spanSubTypeRepository.save(spanSubType);
        return null;
    }

    @Override
    public SpanSubType update(SpanSubType spanSubType) {
        SpanSubType byNameAndIdNot = spanSubTypeRepository.findByClaimSubType_IdAndClaimBehaviourSetting_IdAndIdNot(spanSubType.getClaimSubTypeId(), spanSubType.getClaimBehaviourSettingId(), spanSubType.getId());
        if(Objects.nonNull(byNameAndIdNot)){
            throw new ServiceException("Span sub type already exists. Can't update!");
        }

        SpanSubType savedClaimBehaviourSetting = this.spanSubTypeRepository.findById(spanSubType.getId()).orElse(null);
        if(Objects.isNull(savedClaimBehaviourSetting)){
            throw new ServiceException("Span sub type not found. Can't update!");
        }
        if(this.validate(spanSubType)) {
            CommonUtils.copyNonNullProperties(spanSubType, savedClaimBehaviourSetting);
            return this.spanSubTypeRepository.save(spanSubType);
        }
        return null;
    }

    @Override
    public SpanSubType find(Long id) {
        return this.spanSubTypeRepository.findById(id).orElse(null);
    }

    @Override
    public List<SpanSubType> findByClaimBehaviourSetting(Long claimBehaviourSettingId) {
        return this.spanSubTypeRepository.findByClaimBehaviourSetting_Id(claimBehaviourSettingId);
    }

    private boolean validate(SpanSubType spanSubType){
        return true;
    }

}
