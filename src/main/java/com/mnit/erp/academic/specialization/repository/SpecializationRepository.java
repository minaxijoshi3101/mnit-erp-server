package com.mnit.erp.academic.specialization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.branch.model.Branch;
import com.mnit.erp.academic.specialization.model.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    List<Specialization> findByBranch(Branch branch);
    
    List<Specialization> findByNameContaining(String searchStrig);
    
    Specialization findByName(String searchStrig);
}
