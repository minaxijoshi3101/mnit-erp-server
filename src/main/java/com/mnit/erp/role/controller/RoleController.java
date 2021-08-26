package com.mnit.erp.role.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.role.model.Role;
import com.mnit.erp.role.service.RoleService;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/role")
public class RoleController extends AbstractController {

    @Autowired
    RoleService roleService;

    @PostMapping("add")
    public CustomResponseMessage add(@RequestBody Role role){
        Role role1 = this.roleService.add(role);
        ResponseMessageType responseMessageType = Objects.nonNull(role1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(role1) ? "User role added successfully in masters!" : "Unable to add user role in masters!")
                .messageType(responseMessageType)
                .response(role1).build();
    }

    @PutMapping("update")
    public CustomResponseMessage update(@RequestBody Role role){
        Role role1 = this.roleService.update(role);
        ResponseMessageType responseMessageType = Objects.nonNull(role1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(role1) ? "User role updated successfully in masters!" : "Unable to update user role in masters!")
                .messageType(responseMessageType)
                .response(role1).build();
    }

    @GetMapping("find/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Role role = this.roleService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(role) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(role) ? "User role found successfully in masters!" : "Unable to find user role in masters!")
                .messageType(responseMessageType)
                .response(role).build();
    }

    @GetMapping("findAll")
    public CustomResponseMessage findAll(){
        List<Role> roles = this.roleService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(roles) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(roles) && roles.size() > 0 ? "User roles loaded successfully from masters!" : "Unable to find user roles in masters!")
                .messageType(responseMessageType)
                .response(roles).build();
    }

}
