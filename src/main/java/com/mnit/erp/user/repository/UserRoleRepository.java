package com.mnit.erp.user.repository;

import com.mnit.erp.role.model.Role;
import com.mnit.erp.user.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByUserIdAndRoleId(Long userId, Long roleId);

    List<UserRole> findByUserId(Long userId);

    List<UserRole> findByAssignedBy(Long userId);

    List<UserRole> findAllByUserId(Long userId);
}
