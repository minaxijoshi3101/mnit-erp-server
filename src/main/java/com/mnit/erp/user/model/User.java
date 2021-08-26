package com.mnit.erp.user.model;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=User.class)
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;
    String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    String email;
    String mobile;
    Boolean enabled;
    Boolean activated;
    Date addedOn;

    @JsonIgnore
    String activationLink;

    public User(Long userId) {
        this.id = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.nonNull(this.enabled) ? this.enabled : false;
    }
}
