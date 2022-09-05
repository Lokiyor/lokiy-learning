package com.lokiy.learning.spring.security.sys.service;

import com.lokiy.learning.spring.security.model.LoginUser;
import com.lokiy.learning.spring.security.util.SecurityUtil;
import org.springframework.stereotype.Service;

/**
 * @author lokiy
 * @date 2022/9/5
 * @description TODO
 */
@Service("pms")
public class PermissionService {


    public boolean hasRole(String role){
        LoginUser currentUser = SecurityUtil.getCurrentUser();
        return currentUser.getAuthorities()
                .stream().anyMatch(t->t.getAuthority().equals(role));
    }


    public boolean hasPermission(String permission){
        LoginUser currentUser = SecurityUtil.getCurrentUser();
        return currentUser.getPermissions()
                .stream().anyMatch(t->t.equals(permission));
    }

}
