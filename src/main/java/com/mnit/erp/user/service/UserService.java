package com.mnit.erp.user.service;

import com.mnit.erp.user.filter.UserFilter;
import com.mnit.erp.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    User findUserById(Long userId);

    User findUserByEmail(String email);

    User findUserByMobile(String mobile);

    User findByActivationLink(String activationLink);

    Page<User> findAllByUserFilter(UserFilter userFilter, Pageable pageable);

    void sendAccountActivationMail(User user);

    void setPassword(String activationLink, String password);

    void forgotPassword(String email);

    void changePassword(String currentPassword, String newPassword, String confirmPassword);

    void savePhoto(String base64Image);

    User removeUser(String userId);

    User findUserByUsername(String username);

    Page<User> findAll(Pageable pageable);
}
