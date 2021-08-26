package com.mnit.erp.family.model;

import com.mnit.erp.family.model.FamilyMemberRelation.FamilyMemberRelation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FamilyMember {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    FamilyMemberRelation relation;
    String name;
    String email;
    String mobile;
    String address;

    Boolean nominee;

}
