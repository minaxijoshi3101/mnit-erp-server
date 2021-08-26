package com.mnit.erp.finance.claimBehaviourSetting.repository;

import com.mnit.erp.finance.claimBehaviourSetting.model.ClaimBehaviourSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contains claim behaviour setting repository
 *
 * @author: Tejpal Singh
 * @date: 16 July, 2021
 */
@Repository
public interface ClaimBehaviourSettingRepository extends JpaRepository<ClaimBehaviourSetting, Long> {
    ClaimBehaviourSetting findByAppointmentNature_IdAndCadre_IdAndDesignation_Id(Long appointmentNatureId, Long CadreId, Long designationId);
    ClaimBehaviourSetting findByAppointmentNature_IdAndCadre_IdAndDesignation_IdAndIdNot(Long appointmentNatureId, Long CadreId, Long designationId, Long claimBehaviourId);
}
