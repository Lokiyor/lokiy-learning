package com.lokiy.learning.spring.security.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author lokiy
 * @date 2022/9/5
 * @description TODO
 */
public class SysGrantedAuthority implements GrantedAuthority {

    private String authority;

    public SysGrantedAuthority(){
        super();
    }

    public SysGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SysGrantedAuthority) {
            return this.authority.equals(((SysGrantedAuthority) obj).authority);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.authority.hashCode();
    }

    @Override
    public String toString() {
        return this.authority;
    }

}
