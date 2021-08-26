package com.mnit.erp.user.repository;

import com.mnit.erp.user.model.UserRoleAssignmentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleAssignmentLogRepository extends JpaRepository<UserRoleAssignmentLog, Long> {
}
