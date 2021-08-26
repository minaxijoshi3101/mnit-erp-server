package com.mnit.erp.department.service;

import com.mnit.erp.department.model.Department;
import com.mnit.erp.department.repository.DepartmentRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department add(Department department) {
        Department department1 = this.departmentRepository.findByName(department.getName());
        if(Objects.nonNull(department1)){
            throw new ServiceException("Department "+ department.getName() +" already exists. Can't add new!");
        }
        if(this.validate(department))
            return this.departmentRepository.save(department);
        return null;
    }

    @Override
    public Department update(Department department) {
        Department department1 = this.departmentRepository.findById(department.getId()).orElse(null);
        if(Objects.isNull(department1)){
            throw new ServiceException("Department not found. Can't update!");
        }
        if(this.validate(department)) {
            CommonUtils.copyNonNullProperties(department, department1);
            return this.departmentRepository.save(department);
        }
        return null;
    }

    @Override
    public Department find(Long id) {
        return this.departmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    private boolean validate(Department department){
        return true;
    }
}
