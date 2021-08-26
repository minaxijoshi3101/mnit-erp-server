package com.mnit.erp.finance.claimActivity.service;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.finance.claimActivity.model.ClaimActivity;
import com.mnit.erp.finance.claimActivity.repository.ClaimActivityRepository;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains Claim Activity Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 14 July, 2021
 */
@Service
public class ClaimActivityServiceImpl implements ClaimActivityService {

    @Autowired
    ClaimActivityRepository claimActivityRepository;

    @Override
    public ClaimActivity add(ClaimActivity claimActivity) {
        ClaimActivity byName = claimActivityRepository.findByNameAndClaim_IdAndClaimSubType_Id(claimActivity.getName(), claimActivity.getClaimId(), claimActivity.getClaimSubTypeId());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Claim activity:" + claimActivity.getName() + " already exists. Can't add again!");
        }

        if(this.validate(claimActivity))
            return this.claimActivityRepository.save(claimActivity);
        return null;
    }

    @Override
    public ClaimActivity update(ClaimActivity claimActivity) {
        ClaimActivity byNameAndIdNot = claimActivityRepository.findByNameAndClaim_IdAndClaimSubType_IdAndIdNot(claimActivity.getName(), claimActivity.getClaimId(), claimActivity.getClaimSubTypeId(), claimActivity.getId());
        if(Objects.nonNull(byNameAndIdNot)){
            throw new ServiceException("Claim activity:" + claimActivity.getName() + " already exists. Can't update!");
        }

        ClaimActivity savedClaimActivity = this.claimActivityRepository.findById(claimActivity.getId()).orElse(null);
        if(Objects.isNull(savedClaimActivity)){
            throw new ServiceException("Claim activity not found. Can't update!");
        }
        if(this.validate(claimActivity)) {
            CommonUtils.copyNonNullProperties(claimActivity, savedClaimActivity);
            return this.claimActivityRepository.save(claimActivity);
        }
        return null;
    }

    @Override
    public ClaimActivity find(Long id) {
        return this.claimActivityRepository.findById(id).orElse(null);
    }

    @Override
    public List<ClaimActivity> findAll() {
        return this.claimActivityRepository.findAll();
    }

    private boolean validate(ClaimActivity claimActivity){
        return true;
    }

}
