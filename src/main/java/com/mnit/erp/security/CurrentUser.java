package com.mnit.erp.security;

import com.mnit.erp.user.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {
    public static User getCurrentInMemoryUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User)
            return (User) authentication.getPrincipal();
        return null;
    }

}
