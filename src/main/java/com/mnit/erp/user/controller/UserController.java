package com.mnit.erp.user.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.user.model.User;
import com.mnit.erp.user.service.UserService;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractController {

    @Autowired
    UserService userService;

    @PostMapping("add")
    public CustomResponseMessage createUser(@RequestBody User user) {
        User user1 = this.userService.createUser(user);
        ResponseMessageType responseMessageType = Objects.nonNull(user1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(user1) ? "User added successfully!" : "Unable to add user!")
                .messageType(responseMessageType)
                .response(user1).build();
    }

    @PutMapping("update")
    public CustomResponseMessage updateUser(@RequestBody User user) {
        User user1 = this.userService.updateUser(user);
        ResponseMessageType responseMessageType = Objects.nonNull(user1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(user1) ? "User updated successfully!" : "Unable to update user!")
                .messageType(responseMessageType)
                .response(user1).build();
    }

    @GetMapping("find/id/{userId}")
    public CustomResponseMessage findUserById(@PathVariable Long userId) {
        User user1 = this.userService.findUserById(userId);
        ResponseMessageType responseMessageType = Objects.nonNull(user1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(user1) ? "User found successfully!" : "Unable to find user!")
                .messageType(responseMessageType)
                .response(user1).build();
    }

    @GetMapping("find/username/{username}")
    public CustomResponseMessage findUserByUsername(@PathVariable String username) {
        User user1 = this.userService.findUserByUsername(username);
        ResponseMessageType responseMessageType = Objects.nonNull(user1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(user1) ? "User : " + username + " found successfully!" : "Unable to find user!")
                .messageType(responseMessageType)
                .response(user1).build();
    }

    @GetMapping("find/email/{email}")
    public CustomResponseMessage findUserByEmail(@PathVariable String email) {
        User user1 = this.userService.findUserByEmail(email);
        ResponseMessageType responseMessageType = Objects.nonNull(user1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(user1) ? "User : " + email + " found successfully!" : "Unable to find user!")
                .messageType(responseMessageType)
                .response(user1).build();
    }

    @GetMapping("find/mobile/{mobile}")
    public CustomResponseMessage findUserByMobile(@PathVariable String mobile) {
        User user1 = this.userService.findUserByMobile(mobile);
        ResponseMessageType responseMessageType = Objects.nonNull(user1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(user1) ? "User : " + mobile + " found successfully!" : "Unable to find user!")
                .messageType(responseMessageType)
                .response(user1).build();
    }

    @GetMapping("findAll")
    public CustomResponseMessage findAll(Pageable pageable) {
        Page<User> users = this.userService.findAll(pageable);
        ResponseMessageType responseMessageType = Objects.nonNull(users) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(users) && !users.isEmpty() ? "Users found successfully!" : "Unable to find users!")
                .messageType(responseMessageType)
                .response(users).build();
    }

}
