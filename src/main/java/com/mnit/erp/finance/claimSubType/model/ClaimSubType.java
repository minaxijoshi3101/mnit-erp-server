package com.mnit.erp.finance.claimSubType.model;

import com.mnit.erp.finance.claim.model.Claim;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Contains claim sub type model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of claim sub type model
 * @date: 13 July, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClaimSubType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    Boolean status;

    public ClaimSubType(Long claimSubTypeId) {
        this.id = claimSubTypeId;
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

}
