package com.mnit.erp.user.service;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.security.WebSecurityConfig;
import com.mnit.erp.user.filter.UserFilter;
import com.mnit.erp.user.model.User;
import com.mnit.erp.user.repository.UserRepository;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        List<User> users = this.userRepository.findByUsernameOrEmailOrMobile(user.getUsername(), user.getEmail(), user.getMobile());
        if (Objects.nonNull(users) && users.size() > 0) {
            throw new ServiceException("User with username : " + user.getUsername() + " or email : " + user.getEmail() + " or mobile : " + user.getMobile() + " already exists. Can't add new!");
        }
        if (this.validateUser(user)) {
            user.setActivationLink(UUID.randomUUID().toString());
            if (Objects.isNull(user.getPassword())) {
                user.setPassword(UUID.randomUUID().toString());
            }
            user.setPassword(WebSecurityConfig.getMd5(user.getPassword()));
            User savedUser = this.userRepository.save(user);
            this.sendAccountActivationMail(savedUser);
            return savedUser;
        }
        return null;
    }

    @Override
    public User findUserById(Long userId) {
        return this.userRepository.findById(userId).orElse(null);
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User findUserByMobile(String mobile) {
        return this.userRepository.findByMobile(mobile);
    }

    @Override
    public Page<User> findAllByUserFilter(UserFilter userFilter, Pageable pageable) {
        return null;
    }

    @Override
    public User findByActivationLink(String activationLink) {
        return null;
    }

    @Override
    public void sendAccountActivationMail(User user) {
        System.out.println("Account activation link : " + user.getActivationLink());
    }

    @Override
    public void setPassword(String activationLink, String password) {

    }

    @Override
    public void forgotPassword(String email) {

    }

    @Override
    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {

    }

    @Override
    public void savePhoto(String base64Image) {

    }

    @Override
    public User removeUser(String userId) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        User savedUser = this.userRepository.findById(user.getId()).orElse(null);
        if(Objects.isNull(savedUser)){
            throw new ServiceException("Invalid user id. Unable to update!");
        }
        if (this.validateUser(user)) {
            user.setUsername(null);
            user.setPassword(null);
            CommonUtils.copyNonNullProperties(user, savedUser);
            return this.userRepository.save(savedUser);
        }
        return null;
    }

    private boolean validateUser(User user) {
        if (Objects.nonNull(user))
            return true;
        return false;
    }

}
