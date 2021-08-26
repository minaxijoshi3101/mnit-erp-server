package com.mnit.erp.finance.blockSubType.model;

import com.mnit.erp.finance.claimBehaviourSetting.model.ClaimBehaviourSetting;
import com.mnit.erp.finance.claimSubType.model.ClaimSubType;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * Contains block sub type model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of block sub type model
 * @date: 19 July, 2021
 */
@Data
@Entity
public class BlockSubType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(nullable = false)
    Double amount;

    @Column(nullable = false, columnDefinition = "Boolean default false")
    Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="claim_subtype_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private ClaimSubType claimSubType;

    @Transient
    Long claimSubTypeId;
    public void setClaimSubTypeId(Long claimSubTypeId){
        this.claimSubType = Objects.nonNull(claimSubTypeId) ? new ClaimSubType(claimSubTypeId) : null;
    }
    public Long getClaimSubTypeId(){
        return Objects.nonNull(this.claimSubType) ? this.claimSubType.getId() : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="claim_behaviour_setting_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private ClaimBehaviourSetting claimBehaviourSetting;

    @Transient
    Long claimBehaviourSettingId;
    public void setClaimBehaviourSettingId(Long claimBehaviourSettingId){
        this.claimBehaviourSetting = Objects.nonNull(claimBehaviourSettingId) ? new ClaimBehaviourSetting(claimBehaviourSettingId) : null;
    }
    public Long getClaimBehaviourSettingId(){
        return Objects.nonNull(this.claimBehaviourSetting) ? this.claimBehaviourSetting.getId() : null;
    }

}
