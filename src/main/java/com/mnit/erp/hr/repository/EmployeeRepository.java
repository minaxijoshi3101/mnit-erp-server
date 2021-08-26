package com.mnit.erp.hr.repository;

import com.mnit.erp.department.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.hr.model.Employee;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Prahalad
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Optional<Employee> findByEmployeeCode(String name);
    List<Employee> findByDepartment(Department department);
}
