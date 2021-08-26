package com.mnit.erp.user.repository;

import com.mnit.erp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findByMobile(String mobile);

    List<User> findByUsernameOrEmailOrMobile(String userUsername, String email, String username);
}
