package com.mnit.erp.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserRoleAssignmentLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userRoleId;
    Long userId;
    Long roleId;
    Date assignedOn;
    Date validUpTo;
    String remarks;
    Long assignedBy;
    RoleAssignmentType roleAssignmentType;
    Date removedOn;
    Long removedBy;

    public UserRoleAssignmentLog(UserRole userRole){
        this.setUserRoleId(userRole.getId());
        this.setUserId(userRole.getUserId());
        this.setRoleId(userRole.getId());
        this.setAssignedOn(userRole.getAssignedOn());
        this.setValidUpTo(userRole.getValidUpTo());
        this.setRemarks(userRole.getRemarks());
        this.setAssignedBy(userRole.getAssignedBy());
        this.setRoleAssignmentType(userRole.getRoleAssignmentType());
    }

}
