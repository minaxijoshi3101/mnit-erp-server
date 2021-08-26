package com.mnit.erp.finance.claimActivity.model;

import com.mnit.erp.finance.claim.model.Claim;
import com.mnit.erp.finance.claimSubType.model.ClaimSubType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Contains claim activity model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of claim activity model
 * @date: 14 July, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClaimActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    ClaimPeriodicity claimPeriodicity;
    @Column(nullable = false)
    Boolean status;

    public ClaimActivity(Long claimActivityId) {
        this.id = claimActivityId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="claim_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Claim claim;

    @Transient
    Long claimId;
    public void setClaimId(Long claimId){
        this.claim = Objects.nonNull(claimId) ? new Claim(claimId) : null;
    }
    public Long getClaimId(){
        return Objects.nonNull(this.claim) ? this.claim.getId() : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="claim_sub_type_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private ClaimSubType claimSubType;

    @Transient
    Long claimSubTypeId;
    public void setClaimSubTypeId(Long claimSubTypeId){
        this.claimSubType = Objects.nonNull(claimSubTypeId) ? new ClaimSubType(claimSubTypeId) : null;
    }
    public Long getClaimSubTypeId(){
        return Objects.nonNull(this.claimSubType) ? this.claimSubType.getId() : null;
    }
}
