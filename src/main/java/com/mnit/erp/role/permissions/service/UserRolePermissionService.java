package com.mnit.erp.role.permissions.service;

import com.mnit.erp.content.model.Menu;
import com.mnit.erp.content.repository.MenuRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.role.model.Role;
import com.mnit.erp.role.permissions.model.MenuUserAccess;
import com.mnit.erp.role.permissions.repository.UserRolePermissionRepository;
import com.mnit.erp.role.repository.RoleRepository;
import com.mnit.erp.user.model.UserRole;
import com.mnit.erp.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserRolePermissionService {

    @Autowired
    UserRolePermissionRepository userRolePermissionRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MenuRepository menuRepository;

    public MenuUserAccess add(MenuUserAccess userRolePermission) {
        Role role = this.roleRepository.findById(userRolePermission.getRole().getId()).orElse(null);
        if(Objects.isNull(role)){
            throw new ServiceException("Invalid role. Can't add permission!");
        }
        Menu menu = this.menuRepository.findById(userRolePermission.getMenu().getId()).orElse(null);
        if(Objects.isNull(menu)){
            throw new ServiceException("Requested menu doesn't exist. Please check!");
        }
        MenuUserAccess byUserRoleAndMenu = this.userRolePermissionRepository.findByRoleAndMenu(role, menu);
        if(Objects.nonNull(byUserRoleAndMenu)){
            throw new ServiceException("Permission already granted to the user-role!");
        }
        if(this.validate(userRolePermission)){
            return this.userRolePermissionRepository.save(userRolePermission);
        }
        return null;
    }

    private boolean validate(MenuUserAccess userRolePermission){
        return true;
    }

    public MenuUserAccess update(MenuUserAccess userRolePermission) {
        if(this.validate(userRolePermission)){
            return this.userRolePermissionRepository.save(userRolePermission);
        }
        return null;
    }

    public MenuUserAccess find(Long id) {
        return this.userRolePermissionRepository.findById(id).orElse(null);
    }

    public Page<MenuUserAccess> findAll(Pageable pageable) {
        return this.userRolePermissionRepository.findAll(pageable);
    }

    public Page<MenuUserAccess> findAllPermissionOfUserRole(Long userRoleId, Pageable pageable) {
        return this.userRolePermissionRepository.findByRole(new Role(userRoleId), pageable);
    }

    public Page<MenuUserAccess> findAllUserRolesWithMenu(Long menuId, Pageable pageable) {
        return this.userRolePermissionRepository.findByMenu(new Menu(menuId), pageable);
    }

    public Boolean revokeUserRolePermission(Long userRolePermissionId, Long userRoleId, Long menuId) {
        MenuUserAccess userRolePermission = this.userRolePermissionRepository.findById(userRolePermissionId).orElse(null);
        if(Objects.isNull(userRolePermission) || userRolePermission.getRole().getId() != userRoleId || userRolePermission.getMenu().getId() != menuId){
            throw new ServiceException("Invalid user role permission. Can't revoke!");
        }
        // TODO :
        // capture logs of revoking permission here
        this.userRolePermissionRepository.delete(userRolePermission);
        return true;
    }

    public List<MenuUserAccess> findAllPermissionsOfRoles(List<Role> roles){
        return this.userRolePermissionRepository.findAllByRoleIn(roles);
    }

    public List<MenuUserAccess> findAllPermissionsOfUser(Long userId){
        return this.findAllPermissionsOfRoles(this.userRoleService.findAllRolesOfUser(userId));
    }

}
