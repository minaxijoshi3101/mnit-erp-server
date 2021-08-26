package com.mnit.erp.content.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.user.model.UserRole;
import com.mnit.erp.util.EntityIdResolver;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=Menu.class)
public class Menu  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title;
    String description;
    Long parentId;
    Long moduleId;
    Boolean status;

    @OneToMany(mappedBy = "parentId")
    List<Menu> children;

    public Menu(Long menuId) {
        this.id = menuId;
    }
}
