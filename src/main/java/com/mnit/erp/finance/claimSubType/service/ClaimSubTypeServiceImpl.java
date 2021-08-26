package com.mnit.erp.finance.claimSubType.service;

import com.mnit.erp.finance.claimSubType.model.ClaimSubType;
import com.mnit.erp.finance.claimSubType.repository.ClaimSubTypeRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains Claim Sub Type Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 13 July, 2021
 */
@Service
public class ClaimSubTypeServiceImpl implements ClaimSubTypeService {

    @Autowired
    ClaimSubTypeRepository claimSubTypeRepository;

    @Override
    public ClaimSubType add(ClaimSubType claimSubType) {
        ClaimSubType byName = claimSubTypeRepository.findByNameAndClaim_Id(claimSubType.getName(), claimSubType.getClaimId());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Claim sub type:" + claimSubType.getName() + " already exists. Can't add again!");
        }

        if(this.validate(claimSubType))
            return this.claimSubTypeRepository.save(claimSubType);
        return null;
    }

    @Override
    public ClaimSubType update(ClaimSubType claimSubType) {
        ClaimSubType byNameAndIdNot = claimSubTypeRepository.findByNameAndClaim_IdAndIdNot(claimSubType.getName(), claimSubType.getClaimId(), claimSubType.getId());
        if(Objects.nonNull(byNameAndIdNot)){
            throw new ServiceException("Claim sub type:" + claimSubType.getName() + " already exists. Can't update!");
        }

        ClaimSubType savedClaimSubType = this.claimSubTypeRepository.findById(claimSubType.getId()).orElse(null);
        if(Objects.isNull(savedClaimSubType)){
            throw new ServiceException("Claim sub type not found. Can't update!");
        }
        if(this.validate(claimSubType)) {
            CommonUtils.copyNonNullProperties(claimSubType, savedClaimSubType);
            return this.claimSubTypeRepository.save(claimSubType);
        }
        return null;
    }

    @Override
    public ClaimSubType find(Long id) {
        return this.claimSubTypeRepository.findById(id).orElse(null);
    }

    @Override
    public List<ClaimSubType> findAll() {
        return this.claimSubTypeRepository.findAll();
    }

    @Override
    public List<ClaimSubType> findAllUnderClaim(Long claimId) {
        return this.claimSubTypeRepository.findByClaim_Id(claimId);
    }

    private boolean validate(ClaimSubType claimSubType){
        return true;
    }

}
