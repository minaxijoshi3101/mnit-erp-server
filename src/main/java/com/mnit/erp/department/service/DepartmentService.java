package com.mnit.erp.department.service;

import com.mnit.erp.department.model.Department;

import java.util.List;

public interface DepartmentService {
    Department add(Department department);
    Department update(Department department);
    Department find(Long id);
    List<Department> findAll();
}
