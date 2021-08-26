package com.mnit.erp.academic.branch.service;

import com.mnit.erp.academic.branch.model.Branch;
import com.mnit.erp.academic.branch.repository.BranchRepository;
import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.academic.degree.repository.DegreeRepository;
import com.mnit.erp.department.repository.DepartmentRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BranchServiceImpl implements BranchService{

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    DegreeRepository degreeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Branch add(Branch branch) {
        if(validate(branch)){
            return this.branchRepository.save(branch);
        }
        return null;
    }

    @Override
    public Branch update(Branch branch) {
        Branch savedBranch = this.branchRepository.findById(branch.getId()).orElse(null);
        if(Objects.isNull(savedBranch)){
            throw new ServiceException("Branch doesn't exists. Update not possible!");
        }
        if(validate(branch)){
            CommonUtils.copyNonNullProperties(branch, savedBranch);
            return this.branchRepository.save(savedBranch);
        }
        return null;
    }

    @Override
    public Branch find(Long id) {
        return this.branchRepository.findById(id).orElse(null);
    }

    @Override
    public List<Branch> findAllByDegreeId(Long degreeId) {
        Degree degree = this.degreeRepository.findById(degreeId).orElse(null);
        return this.branchRepository.findByDegrees(degree);
    }

    @Override
    public List<Branch> findAll() {
        return this.branchRepository.findAll();
    }

    @Override
    public List<Branch> findAllByProgramId(Long programId) {
//        return this.branchRepository.findByProgramId(programId);
        return null;
    }


    @Override
    public List<Branch> findAllByDepartmentId(Long departmentId) {
        return this.branchRepository.findByDepartment(this.departmentRepository.findById(departmentId).orElse(null));
    }

    private boolean validate(Branch branch){
        return true;
    }

}
