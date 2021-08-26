package com.mnit.erp.finance.claimBehaviourSetting.service;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.finance.claimBehaviourSetting.model.ClaimBehaviourSetting;
import com.mnit.erp.finance.claimBehaviourSetting.repository.ClaimBehaviourSettingRepository;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains Claim behaviour setting Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 16 July, 2021
 */
@Service
public class ClaimBehaviourSettingServiceImpl implements ClaimBehaviourSettingService {

    @Autowired
    ClaimBehaviourSettingRepository claimBehaviourSettingRepository;

    @Override
    public ClaimBehaviourSetting add(ClaimBehaviourSetting claimBehaviourSetting) {
        ClaimBehaviourSetting byName = claimBehaviourSettingRepository.findByAppointmentNature_IdAndCadre_IdAndDesignation_Id(claimBehaviourSetting.getAppointmentNatureId(), claimBehaviourSetting.getCadreId(), claimBehaviourSetting.getDesignationId());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Claim behaviour setting already exists. Can't add again!");
        }

        if(this.validate(claimBehaviourSetting))
            return this.claimBehaviourSettingRepository.save(claimBehaviourSetting);
        return null;
    }

    @Override
    public ClaimBehaviourSetting update(ClaimBehaviourSetting claimBehaviourSetting) {
        ClaimBehaviourSetting byNameAndIdNot = claimBehaviourSettingRepository.findByAppointmentNature_IdAndCadre_IdAndDesignation_IdAndIdNot(claimBehaviourSetting.getAppointmentNatureId(), claimBehaviourSetting.getCadreId(), claimBehaviourSetting.getDesignationId(), claimBehaviourSetting.getId());
        if(Objects.nonNull(byNameAndIdNot)){
            throw new ServiceException("Claim behaviour setting already exists. Can't update!");
        }

        ClaimBehaviourSetting savedClaimBehaviourSetting = this.claimBehaviourSettingRepository.findById(claimBehaviourSetting.getId()).orElse(null);
        if(Objects.isNull(savedClaimBehaviourSetting)){
            throw new ServiceException("Claim behaviour setting not found. Can't update!");
        }
        if(this.validate(claimBehaviourSetting)) {
            CommonUtils.copyNonNullProperties(claimBehaviourSetting, savedClaimBehaviourSetting);
            return this.claimBehaviourSettingRepository.save(claimBehaviourSetting);
        }
        return null;
    }

    @Override
    public ClaimBehaviourSetting find(Long id) {
        return this.claimBehaviourSettingRepository.findById(id).orElse(null);
    }

    @Override
    public List<ClaimBehaviourSetting> findAll() {
        return this.claimBehaviourSettingRepository.findAll();
    }

    private boolean validate(ClaimBehaviourSetting claimBehaviourSetting){
        return true;
    }

}
