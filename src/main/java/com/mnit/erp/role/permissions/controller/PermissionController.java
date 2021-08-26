package com.mnit.erp.role.permissions.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.role.permissions.model.MenuUserAccess;
import com.mnit.erp.role.permissions.service.UserRolePermissionService;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/manage-permissions")
public class PermissionController extends AbstractController {

    @Autowired
    UserRolePermissionService userRolePermissionService;

    @PostMapping
    public CustomResponseMessage addPermission(@RequestBody MenuUserAccess userRolePermission){
        MenuUserAccess userRolePermission1 = this.userRolePermissionService.add(userRolePermission);
        ResponseMessageType responseMessageType = Objects.nonNull(userRolePermission1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(userRolePermission1) ? "Permission granted to user role successfully!" : "Unable to grant permission to user role!")
                .messageType(responseMessageType)
                .response(userRolePermission1).build();
    }

    @PutMapping
    public CustomResponseMessage updatePermission(@RequestBody MenuUserAccess userRolePermission){
        MenuUserAccess userRolePermission1 = this.userRolePermissionService.update(userRolePermission);
        ResponseMessageType responseMessageType = Objects.nonNull(userRolePermission1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(userRolePermission1) ? "User role permission modified successfully!" : "Unable to modify permission of user role!")
                .messageType(responseMessageType)
                .response(userRolePermission1).build();
    }

    @GetMapping("/findById/{id}")
    public CustomResponseMessage findPermissionById(@PathVariable Long id){
        MenuUserAccess userRolePermission1 = this.userRolePermissionService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(userRolePermission1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(userRolePermission1) ? "Permission found successfully!" : "Unable to find permission!")
                .messageType(responseMessageType)
                .response(userRolePermission1).build();
    }

    @GetMapping("/findAll")
    public CustomResponseMessage findAll(Pageable pageable){
        Page<MenuUserAccess> permissions = this.userRolePermissionService.findAll(pageable);
        ResponseMessageType responseMessageType = Objects.nonNull(permissions) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(permissions) ? "Permissions found!" : "Unable to find permissions!")
                .messageType(responseMessageType)
                .response(permissions).build();
    }

    @GetMapping("/findAllPermissionsOfUserRole/{userRoleId}")
    public CustomResponseMessage findAllPermissionsOfUserRole(@PathVariable Long userRoleId, Pageable pageable){
        Page<MenuUserAccess> permissionsOfUserRole = this.userRolePermissionService.findAllPermissionOfUserRole(userRoleId, pageable);
        ResponseMessageType responseMessageType = Objects.nonNull(permissionsOfUserRole) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(permissionsOfUserRole) && permissionsOfUserRole.getTotalElements() != 0? "Permissions of user role found successfully!" : "Unable to find permissions for user role!")
                .messageType(responseMessageType)
                .response(permissionsOfUserRole).build();
    }

    @GetMapping("/findAllUserRolePermissionsForMenu/{menuId}")
    public CustomResponseMessage findAllUserRolePermissionsForMenu(@PathVariable Long menuId, Pageable pageable){
        Page<MenuUserAccess> permissionsWithMenu = this.userRolePermissionService.findAllUserRolesWithMenu(menuId, pageable);
        ResponseMessageType responseMessageType = Objects.nonNull(permissionsWithMenu) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(permissionsWithMenu) && permissionsWithMenu.getTotalElements() != 0? "Permissions of menu found successfully!" : "Unable to find permissions for given menu!")
                .messageType(responseMessageType)
                .response(permissionsWithMenu).build();
    }

    @DeleteMapping("/revokePermission/{userRolePermissionId}/{userRoleId}/{menuId}")
    public CustomResponseMessage revokeUserRolePermission(@PathVariable Long userRolePermissionId, @PathVariable Long userRoleId, @PathVariable Long menuId){
        Boolean status = this.userRolePermissionService.revokeUserRolePermission(userRolePermissionId, userRoleId, menuId);
        ResponseMessageType responseMessageType = Objects.nonNull(status) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(status) ? "Permission revoked successfully!" : "Unable to revoke permission!")
                .messageType(responseMessageType)
                .response(status).build();
    }

}
