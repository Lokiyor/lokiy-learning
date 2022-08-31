package com.lokiy.learning.spring.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author lokiy
 * @date 2022/8/31
 * @description TODO
 */
public class LoginUser implements UserDetails {

    private Long userId;
    private String username;
    private String password;
    private Long tenantId;
    private boolean enabled;

    private Collection<GrantedAuthority> authorities;


    public Long getUserId() {
        return userId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return "NaN";
    }


    @Override
    public String getUsername() {
        return this.username;
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
        return true;
    }
}
