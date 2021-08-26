package com.mnit.erp.user.service;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.role.model.Role;
import com.mnit.erp.role.repository.RoleRepository;
import com.mnit.erp.role.service.RoleService;
import com.mnit.erp.security.CurrentUser;
import com.mnit.erp.user.model.RoleAssignmentType;
import com.mnit.erp.user.model.User;
import com.mnit.erp.user.model.UserRole;
import com.mnit.erp.user.model.UserRoleAssignmentLog;
import com.mnit.erp.user.repository.UserRepository;
import com.mnit.erp.user.repository.UserRoleAssignmentLogRepository;
import com.mnit.erp.user.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRoleAssignmentLogRepository userRoleAssignmentLogRepository;

    public UserRole assignRoleTOUser(UserRole userRole) {
        if(this.validateRoleAssignment(userRole)){
            this.prepareUserRole(userRole);
            userRole.setRoleAssignmentType(RoleAssignmentType.PERMANENT_ROLE);
            return this.userRoleRepository.save(userRole);
        }
        return null;
    }

    private boolean validateRoleAssignment(UserRole userRole){
        if(userRole.getUserId() == CurrentUser.getCurrentInMemoryUser().getId()){
            throw new ServiceException("You can't assign role to self!");
        }
        User user = this.userRepository.findById(userRole.getUserId()).orElse(null);
        if(Objects.isNull(user)){
            throw new ServiceException("User doesn't exists. Can't modify user roles!");
        }
        Role role = this.roleRepository.findById(userRole.getRoleId()).orElse(null);
        if(Objects.isNull(role)){
            throw new ServiceException("Role doesn't exists. Can't assign role!");
        }
        UserRole byUserIdAndRoleId = this.userRoleRepository.findByUserIdAndRoleId(userRole.getUserId(), userRole.getRoleId());
        if(Objects.nonNull(byUserIdAndRoleId)){
            throw new ServiceException("Role "+ role.getName()+" already assigned to user!");
        }
        return true;
    }

    private UserRole prepareUserRole(UserRole userRole){
        userRole.setAssignedOn(new Date());
        userRole.setAssignedBy(CurrentUser.getCurrentInMemoryUser().getId());
        return userRole;
    }

    public UserRole removeRoleFromUser(UserRole userRole) {
        if(Objects.nonNull(userRole.getId())){
            UserRole userRole1 = this.userRoleRepository.findById(userRole.getId()).orElse(null);
            if(Objects.isNull(userRole1) || userRole.getUserId() != userRole1.getUserId() || userRole.getRoleId() != userRole1.getRoleId()){
                throw new ServiceException("Invalid operation. Role can't be removed from user!");
            }
            // TODO : capture role removal history
            this.userRoleRepository.delete(userRole1);
            this.saveUserRoleAssignmentLog(userRole1);
            return userRole1;
        }
        return null;
    }

    public Page<UserRole> findAll(Pageable pageable) {
        Page<UserRole> all = this.userRoleRepository.findAll(pageable);
        all.forEach(userRole -> userRole.setRole(this.roleService.find(userRole.getRoleId())));
        return all;
    }

    public List<UserRole> findAllOfUser(Long userId) {
        List<UserRole> userRoles = this.userRoleRepository.findByUserId(userId);
        userRoles.forEach(userRole -> userRole.setRole(this.roleService.find(userRole.getRoleId())));
        return userRoles;
    }

    public List<UserRole> findAllAssignedByUser(Long userId) {
        List<UserRole> assignedBy = this.userRoleRepository.findByAssignedBy(userId);
        assignedBy.forEach(userRole -> userRole.setRole(this.roleService.find(userRole.getRoleId())));
        return assignedBy;
    }

    public UserRole delegateUserRole(UserRole userRole) {
        User currentUser = CurrentUser.getCurrentInMemoryUser();
        if(userRole.getUserId() == currentUser.getId()){
            throw new ServiceException("You can't delegate a role to self!");
        }
        if(Objects.nonNull(userRole.getId())){
            throw new ServiceException("Existing roles can't be modified to a delegated role!");
        }
        if(this.validateRoleAssignment(userRole)){
            userRole.setRoleAssignmentType(RoleAssignmentType.DELEGATED_ROLE);
            userRole.setAssignedOn(new Date());
            userRole.setAssignedBy(currentUser.getId());
            UserRole byUserIdAndRoleId = this.userRoleRepository.findByUserIdAndRoleId(currentUser.getId(), userRole.getRoleId());
            if(Objects.isNull(byUserIdAndRoleId)){
                throw new ServiceException("This role is not assigned to you. You can't delegate!");
            }
            // TODO : Default time limit for delegated role is to be 1 month
            if(Objects.isNull(userRole.getValidUpTo())){
                throw new ServiceException("Delegated role must have a validity period!");
            }
            return this.userRoleRepository.save(userRole);
        }
        return null;
    }

    public UserRole revokeDelegatedRoleFromUser(UserRole userRole) {
        UserRole userRole1 = this.userRoleRepository.findById(userRole.getId()).orElse(null);
        if(Objects.isNull(userRole1)){
            throw new ServiceException("Invalid operation. Can't complete the request!");
        }
        // TODO : you can revoke role assigned by you only
        if(userRole1.getUserId() != userRole.getUserId() || userRole1.getRoleId() != userRole.getRoleId()){
            throw new ServiceException("Invalid user and role combination. Can't revoke!");
        }
        if(userRole1.getAssignedBy() != CurrentUser.getCurrentInMemoryUser().getId()){
            throw new ServiceException("You can't revoke the role because it is not delegated by you!");
        }
        if(userRole1.getUserId() == CurrentUser.getCurrentInMemoryUser().getId()){
            throw new ServiceException("You can't revoke self delegated role!");
        }
        // TODO : capture logs of role delegation here
        this.userRoleRepository.delete(userRole1);
        this.saveUserRoleAssignmentLog(userRole1);
        return userRole1;
    }

    public UserRole assignTempRole(UserRole userRole) {
        if(this.validateRoleAssignment(userRole)){
            if(Objects.isNull(userRole.getValidUpTo())){
                throw new ServiceException("This type of role assignment should have a validity period!");
            }
            userRole.setAssignedBy(CurrentUser.getCurrentInMemoryUser().getId());
            userRole.setAssignedOn(new Date());
            userRole.setRoleAssignmentType(RoleAssignmentType.TEMP_ROLE);
            return this.userRoleRepository.save(userRole);
        }
        return null;
    }

    private UserRoleAssignmentLog saveUserRoleAssignmentLog(UserRole userRole){
        UserRoleAssignmentLog userRoleAssignmentLog = new UserRoleAssignmentLog(userRole);
        userRoleAssignmentLog.setRemovedOn(new Date());
        userRoleAssignmentLog.setRemovedBy(CurrentUser.getCurrentInMemoryUser().getId());
        UserRoleAssignmentLog savedLog = this.userRoleAssignmentLogRepository.save(userRoleAssignmentLog);
        return savedLog;
    }

    public List<Role> findAllRolesOfUser(Long userId){
        List<UserRole> userRoles = this.userRoleRepository.findAllByUserId(userId);
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        return this.roleRepository.findAllByIdIn(roleIds);
    }

}
