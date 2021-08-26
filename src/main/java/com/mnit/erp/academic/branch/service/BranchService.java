package com.mnit.erp.academic.branch.service;

import com.mnit.erp.academic.branch.model.Branch;

import java.util.List;

public interface BranchService {
    Branch add(Branch branch);
    Branch update(Branch branch);
    Branch find(Long id);
    List<Branch> findAllByDegreeId(Long degreeId);

    List<Branch> findAll();
    List<Branch> findAllByProgramId(Long programId);
    List<Branch> findAllByDepartmentId(Long departmentId);
}
