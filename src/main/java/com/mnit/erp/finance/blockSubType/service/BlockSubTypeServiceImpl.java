package com.mnit.erp.finance.blockSubType.service;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.finance.blockSubType.model.BlockSubType;
import com.mnit.erp.finance.blockSubType.repository.BlockSubTypeRepository;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains Block Sub Type Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 19 July, 2021
 */
@Service
public class BlockSubTypeServiceImpl implements BlockSubTypeService {

    @Autowired
    BlockSubTypeRepository blockSubTypeRepository;

    @Override
    public BlockSubType add(BlockSubType blockSubType) {
        BlockSubType byName = blockSubTypeRepository.findByClaimSubType_Id(blockSubType.getClaimSubTypeId());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Block sub type already exists. Can't add again!");
        }

        if(this.validate(blockSubType))
            return this.blockSubTypeRepository.save(blockSubType);
        return null;
    }

    @Override
    public BlockSubType update(BlockSubType blockSubType) {
        BlockSubType byNameAndIdNot = blockSubTypeRepository.findByClaimSubType_IdAndClaimBehaviourSetting_IdAndIdNot(blockSubType.getClaimSubTypeId(), blockSubType.getClaimBehaviourSettingId(), blockSubType.getId());
        if(Objects.nonNull(byNameAndIdNot)){
            throw new ServiceException("Block sub type already exists. Can't update!");
        }

        BlockSubType savedClaimBehaviourSetting = this.blockSubTypeRepository.findById(blockSubType.getId()).orElse(null);
        if(Objects.isNull(savedClaimBehaviourSetting)){
            throw new ServiceException("Block sub type not found. Can't update!");
        }
        if(this.validate(blockSubType)) {
            CommonUtils.copyNonNullProperties(blockSubType, savedClaimBehaviourSetting);
            return this.blockSubTypeRepository.save(blockSubType);
        }
        return null;
    }

    @Override
    public BlockSubType find(Long id) {
        return this.blockSubTypeRepository.findById(id).orElse(null);
    }

    @Override
    public List<BlockSubType> findByClaimBehaviourSetting(Long claimBehaviourSettingId) {
        return this.blockSubTypeRepository.findByClaimBehaviourSetting_Id(claimBehaviourSettingId);
    }

    private boolean validate(BlockSubType blockSubType){
        return true;
    }

}
