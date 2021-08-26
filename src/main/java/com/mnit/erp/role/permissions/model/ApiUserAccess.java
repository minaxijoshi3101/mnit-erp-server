package com.mnit.erp.role.permissions.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.mnit.erp.content.model.Menu;
import com.mnit.erp.role.model.Role;
import com.mnit.erp.user.model.User;
import com.mnit.erp.user.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ApiUserAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private MenuUserAccess menuUserAccess;
	private String api;
	
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private User createdBy;
	private Date createdOn;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private User updatedBy;
	private Date updatedOn;
}
