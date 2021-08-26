package com.mnit.erp.academic.branch.repository;

import com.mnit.erp.academic.branch.model.Branch;
import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.department.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    List<Branch> findByDegrees(Degree degree);

    List<Branch> findByNameContaining(String stringCellValue);

    List<Branch> findByDepartment(Department department);


    Branch findByName(String searchString);    
}
