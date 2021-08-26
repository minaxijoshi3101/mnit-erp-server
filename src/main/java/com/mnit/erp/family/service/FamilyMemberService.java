package com.mnit.erp.family.service;

import com.mnit.erp.family.model.FamilyMember;
import com.mnit.erp.family.repository.FamilyMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyMemberService {

    @Autowired
    FamilyMemberRepository familyMemberRepository;

}
