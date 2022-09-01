package com.lokiy.learning.spring.security.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author lokiy
 * @date 2022/8/31
 * @description TODO
 */
@Data
public class LoginUser implements UserDetails {

    private UserInfo userInfo;

    private String tenantId;

    private Collection<? extends GrantedAuthority> authorities;

    private Set<String> permissions;

    public LoginUser(UserInfo userInfo,
                     String tenantId,
                     Collection<? extends GrantedAuthority> authorities,
                     Set<String> permissions) {
        this.userInfo = userInfo;
        this.tenantId = tenantId;
        this.authorities = authorities;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }


    @Override
    public String getUsername() {
        return userInfo.getUsername();
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
        return userInfo.getStatus().equals(1);
    }
}
