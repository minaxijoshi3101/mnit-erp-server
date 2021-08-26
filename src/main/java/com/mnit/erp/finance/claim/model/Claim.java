package com.mnit.erp.finance.claim.model;

import com.mnit.erp.finance.common.AccountActivity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Contains claim model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of claim model
 * @date: 13 July, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String abbreviation;
    @Column(nullable = false, unique = true)
    String name;
    @Column(nullable = false)
    ClaimBehaviour claimBehaviour;
    @Column(nullable = false)
    AccountActivity accountActivity;
    @Column(nullable = false)
    Boolean status;

    public Claim(Long claimId) {
        this.id = claimId;
    }
}
