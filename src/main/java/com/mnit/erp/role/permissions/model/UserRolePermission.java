package com.mnit.erp.role.permissions.model;

import com.mnit.erp.content.model.Menu;
import com.mnit.erp.content.model.Module;
import com.mnit.erp.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserRolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    Role role;
    @Transient
    Long roleId;

    public Long getRoleId() {
        return Objects.nonNull(this.role) ? this.role.getId() : null;
    }

    public void setRoleId(Long roleId) {
        if(Objects.nonNull(roleId)) {
            this.role = new Role(roleId);
        }
    }

    @OneToOne
    Module module;
    @Transient
    Long moduleId;

    public Long getModuleId() {
        return Objects.nonNull(this.module) ? this.module.getId() : null;
    }

    public void setModuleId(Long moduleId) {
        if(Objects.nonNull(moduleId)){
            this.module = new Module(moduleId);
        }
    }

    @OneToOne
    Menu menu;
    @Transient
    Long menuId;

    public Long getMenuId() {
        return Objects.nonNull(this.menu) ? this.menu.getId() : null;
    }

    public void setMenuId(Long menuId) {
        if(Objects.nonNull(menuId)){
            this.menu = new Menu(menuId);
        }
    }

}
