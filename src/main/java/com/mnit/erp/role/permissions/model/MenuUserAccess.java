package com.mnit.erp.role.permissions.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.mnit.erp.academic.course.model.CourseScheme;
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
public class MenuUserAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private Role role;
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private UserRole userRole;
	@JsonIdentityReference(alwaysAsId = true)
    private Menu menu;
	
	private String allowAction;
	
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private User createdBy;
	private Date createdOn;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private User updatedBy;
	private Date updatedOn;

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="menuUserAccess")
	private List<ApiUserAccess> apiUserAccessDetails=new ArrayList<ApiUserAccess>(0);
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="menuUserAccess")
	private List<FieldUserAccess> fieldUserAccessDetails=new ArrayList<FieldUserAccess>(0);

	@Transient
	private Long menuId;
	public Long getMenuId() {
		return Objects.isNull(menu) ? null : menu.getId();
	}
	/*
	 * @Transient Long roleId;
	 * 
	 * public Long getRoleId() { return Objects.nonNull(this.role) ?
	 * this.role.getId() : null; }
	 * 
	 * public void setRoleId(Long roleId) { if(Objects.nonNull(roleId)) { this.role
	 * = new Role(roleId); } }
	 */
	/*
	 * @Transient Long moduleId;
	 * 
	 * public Long getModuleId() { return Objects.nonNull(this.module) ?
	 * this.module.getId() : null; }
	 * 
	 * public void setModuleId(Long moduleId) { if(Objects.nonNull(moduleId)){
	 * this.module = new Module(moduleId); } }
	 */
	/*
	 * @Transient Long menuId;
	 * 
	 * public Long getMenuId() { return Objects.nonNull(this.menu) ?
	 * this.menu.getId() : null; }
	 * 
	 * public void setMenuId(Long menuId) { if(Objects.nonNull(menuId)){ this.menu =
	 * new Menu(menuId); } }
	 */
}
