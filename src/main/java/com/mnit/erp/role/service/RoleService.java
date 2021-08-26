package com.mnit.erp.role.service;

import com.mnit.erp.role.model.Role;

import java.util.List;

public interface RoleService {

    Role add(Role role);
    Role update(Role role);
    Role find(Long id);
    List<Role> findAll();
}
