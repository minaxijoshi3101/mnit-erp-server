package com.mnit.erp.security;

import com.mnit.erp.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationManagerService authenticationManagerService;

    @Autowired
    AuthSuccess authSuccess;

    @Autowired
    AuthFailure authFailure;

    @Autowired
    LogoutSuccess logoutSuccess;

    @Autowired
    JWTFilter jwtFilter;

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .headers().frameOptions().disable().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/getAuthToken", "/test", "/entity/**","/user/**","/fee/**","/pre_admission/**","/selectionBoard")
                .permitAll()
                .anyRequest().authenticated().and()
                .formLogin().successHandler(authSuccess)
                .failureHandler(authFailure).and()
                .logout()
//                .logoutUrl("/users/logout")
                .logoutSuccessHandler(logoutSuccess)
                .invalidateHttpSession(true)
                .deleteCookies("SESSION", "JSESSIONID")
                .and()
                .exceptionHandling().authenticationEntryPoint(new SecurityAuthenticationEntryPoint())
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(authenticationManagerService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);

    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    private PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return getMd5(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return getMd5(charSequence.toString()).equals(s);
            }
        };
    }

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Invalid hashing algorithm...");
        }
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
