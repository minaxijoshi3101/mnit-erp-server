package com.mnit.erp;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mnit.erp.exceptions.AccessDeniedException;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.security.AuthenticationManagerService;
import com.mnit.erp.security.JWTUtil;
import com.mnit.erp.user.model.User;
import com.mnit.erp.user.model.UserRole;
import com.mnit.erp.user.service.UserRoleService;
import com.mnit.erp.util.ResponseMessageType;

@RestController
@RequestMapping("/getAuthToken")
public class LoginController extends AbstractController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationManagerService userAuthService;

    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    JWTUtil jwtUtil;

    @PostMapping
    public CustomResponseMessage getAuthToken(@RequestBody Map<String, String> body) {
        String username = body.get("username").trim();
        String password = body.get("password");
        if (username == null || password == null)
            throw new ServiceException("Username and Password are required to login!");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new AccessDeniedException("Username or password is invalid. Please try again!");
        } catch (DisabledException de) {
            throw new AccessDeniedException("User is disabled...");
        }
        User details = (User) userAuthService.loadUserByUsername(username);
        details.setPassword(null);
        final String token = jwtUtil.generateToken(details);
        System.out.println("Generated Token :" + token);
        Map<String, Object> map = new TreeMap<String, Object>();
        List<UserRole> userRole = userRoleService.findAllOfUser(details.getId());
        map.put("authToken", token);
        map.put("userRole", userRole);
        map.put("user", details);
        return CustomResponseMessage.builder()
                .messageType(ResponseMessageType.SUCCESS)
                .message("User logged in successfully!")
                .response(map)
                .build();
    }

    @GetMapping("/logout")
    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return true;
        }
        return false;
    }

}
