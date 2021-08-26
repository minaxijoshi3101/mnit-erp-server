package com.mnit.erp.role.service;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.role.model.Role;
import com.mnit.erp.role.repository.RoleRepository;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role add(Role role) {
        if(this.validate(role)){
            return this.roleRepository.save(role);
        }
        return null;
    }

    @Override
    public Role update(Role role) {
        Role role1 = this.roleRepository.findById(role.getId()).orElse(null);
        if(Objects.isNull(role1)){
            throw new ServiceException("User Role not found. Can't update!");
        }
        if(this.validate(role)){
            CommonUtils.copyNonNullProperties(role, role1);
            return this.roleRepository.save(role);
        }
        return null;
    }

    @Override
    public Role find(Long id) {
        return this.roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    private boolean validate(Role role){
        return true;
    }

}

