package com.mnit.erp.user.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.role.model.Role;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=UserRole.class)
public class UserRole implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    Long userId;
    Long roleId;
    @Transient
    Role role;
    Date assignedOn;
    Date validUpTo;
    String remarks;
    Long assignedBy;
    RoleAssignmentType roleAssignmentType;
}
