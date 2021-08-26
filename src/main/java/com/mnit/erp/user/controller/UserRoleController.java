package com.mnit.erp.user.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.user.model.UserRole;
import com.mnit.erp.user.service.UserRoleService;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/manage-user-role")
public class UserRoleController extends AbstractController {

    @Autowired
    UserRoleService userRoleService;

    @PostMapping("/assignRole")
    public CustomResponseMessage assignRoleToUser(@RequestBody UserRole userRole){
        UserRole userRole1 = this.userRoleService.assignRoleTOUser(userRole);
        ResponseMessageType responseMessageType = Objects.nonNull(userRole1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(userRole1) ? "Role assigned successfully!" : "Unable to assign role!")
                .messageType(responseMessageType)
                .response(userRole1).build();
    }

    @PutMapping("/removeRole")
    public CustomResponseMessage removeRoleFromUser(@RequestBody UserRole userRole){
        UserRole userRole1 = this.userRoleService.removeRoleFromUser(userRole);
        ResponseMessageType responseMessageType = Objects.nonNull(userRole1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(userRole1) ? "Role removed successfully!" : "Unable to remove role!")
                .messageType(responseMessageType)
                .response(userRole1).build();
    }

    @GetMapping("findAll")
    public CustomResponseMessage findAll(Pageable pageable){
        Page<UserRole> all = this.userRoleService.findAll(pageable);
        ResponseMessageType responseMessageType = Objects.nonNull(all) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(all) && all.getSize() > 0 ? "Roles found!" : "Unable to find roles!")
                .messageType(responseMessageType)
                .response(all).build();
    }

    @GetMapping("findAllOfUser/{userId}")
    public CustomResponseMessage findAllOfUser(@PathVariable Long userId){
        List<UserRole> all = this.userRoleService.findAllOfUser(userId);
        ResponseMessageType responseMessageType = Objects.nonNull(all) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(all) && !all.isEmpty() ? "Assigned roles of user found!" : "No role(s) assigned to user!")
                .messageType(responseMessageType)
                .response(all).build();
    }

    @GetMapping("findAllAssignedByUser/{userId}")
    public CustomResponseMessage findAllAssignedByUser(@PathVariable Long userId){
        List<UserRole> all = this.userRoleService.findAllAssignedByUser(userId);
        ResponseMessageType responseMessageType = Objects.nonNull(all) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(all) && !all.isEmpty() ? "User assigned roles found!" : "No role(s) assigned by user to anyone!")
                .messageType(responseMessageType)
                .response(all).build();
    }

    @PostMapping("delegateRole")
    public CustomResponseMessage delegateRoleToUser(@RequestBody UserRole userRole){
        UserRole userRole1 = this.userRoleService.delegateUserRole(userRole);
        ResponseMessageType responseMessageType = Objects.nonNull(userRole1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(userRole1) ? "Role delegated successfully!" : "Unable to delegate role!")
                .messageType(responseMessageType)
                .response(userRole1).build();
    }

    @PutMapping("revokeDelegatedRole")
    public CustomResponseMessage revokeDelegatedRoleFromUser(@RequestBody UserRole userRole){
        UserRole userRole1 = this.userRoleService.revokeDelegatedRoleFromUser(userRole);
        ResponseMessageType responseMessageType = Objects.nonNull(userRole1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(userRole1) ? "Delegated role revoked successfully!" : "Unable to revoke delegated role!")
                .messageType(responseMessageType)
                .response(userRole1).build();
    }

    @PostMapping("assignTempRole")
    public CustomResponseMessage assignTempRole(@RequestBody UserRole userRole){
        UserRole userRole1 = this.userRoleService.assignTempRole(userRole);
        ResponseMessageType responseMessageType = Objects.nonNull(userRole1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(userRole1) ? "Role assigned successfully to user!" : "Unable to assign role!")
                .messageType(responseMessageType)
                .response(userRole1).build();
    }

}
