package com.mnit.erp.security;

import com.mnit.erp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthenticationManagerService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = this.userRepository.findByUsername(username);
        if (Objects.nonNull(userDetails))
            return userDetails;
        else throw new UsernameNotFoundException("User not found exception");
    }
}
