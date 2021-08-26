package com.mnit.erp.finance.claim.service;

import com.mnit.erp.finance.claim.model.Claim;
import com.mnit.erp.finance.claim.model.ClaimBehaviour;
import com.mnit.erp.finance.claim.repository.ClaimRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains Claim Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 13 July, 2021
 */
@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    ClaimRepository claimRepository;

    @Override
    public Claim add(Claim claim) {
        Claim byName = claimRepository.findByNameOrAbbreviation(claim.getName(), claim.getAbbreviation());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Claim :" + claim.getName() + " or "+ claim.getAbbreviation()+" already exists. Can't add again!");
        }

        if(this.validate(claim))
            return this.claimRepository.save(claim);
        return null;
    }

    @Override
    public Claim update(Claim claim) {
        Claim byNameAndIdNot = claimRepository.findByNameAndIdNotOrAbbreviationAndIdNot(claim.getName(),claim.getId(), claim.getAbbreviation(),claim.getId());
        if(Objects.nonNull(byNameAndIdNot)){
            throw new ServiceException("Claim:" + claim.getName() +" or "+ claim.getAbbreviation()+" already exists. Can't update!");
        }

        Claim savedClaim = this.claimRepository.findById(claim.getId()).orElse(null);
        if(Objects.isNull(savedClaim)){
            throw new ServiceException("Claim not found. Can't update!");
        }
        if(this.validate(claim)) {
            CommonUtils.copyNonNullProperties(claim, savedClaim);
            return this.claimRepository.save(claim);
        }
        return null;
    }

    @Override
    public Claim find(Long id) {
        return this.claimRepository.findById(id).orElse(null);
    }

    @Override
    public List<Claim> findAll() {
        return this.claimRepository.findAll();
    }

    @Override
    public List<Claim> findAllUnderClaimBehaviour(ClaimBehaviour claimBehaviour) {
        return this.claimRepository.findByClaimBehaviour(claimBehaviour);
    }

    private boolean validate(Claim claim){
        return true;
    }

}
