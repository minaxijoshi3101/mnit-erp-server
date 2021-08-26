package com.mnit.erp.role.permissions.repository;

import com.mnit.erp.content.model.Menu;
import com.mnit.erp.role.model.Role;
import com.mnit.erp.role.permissions.model.MenuUserAccess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolePermissionRepository extends JpaRepository<MenuUserAccess, Long> {

    Page<MenuUserAccess> findByRole(Role role, Pageable pageable);

    MenuUserAccess findByRoleAndMenu(Role role, Menu menu);

    Page<MenuUserAccess> findByMenu(Menu menu, Pageable pageable);

    List<MenuUserAccess> findAllByRoleIn(List<Role> roles);
}
