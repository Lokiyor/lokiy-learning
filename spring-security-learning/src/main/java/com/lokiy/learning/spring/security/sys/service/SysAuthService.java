package com.lokiy.learning.spring.security.sys.service;

import com.lokiy.learning.spring.security.model.UserInfo;
import com.lokiy.learning.spring.security.sys.entity.SysRole;
import com.lokiy.learning.spring.security.sys.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lokiy
 * @date 2022/8/31
 * @description TODO
 */
@Service
public class SysAuthService {

    @Resource
    private ISysUserService sysUserService;

    @Resource
    private ISysPermissionService sysPermissionService;

    @Resource
    private ISysRoleService sysRoleService;

    public String login(String username, String password) {

        return null;
    }


    private UserDetails getUserDetails(String username, String password){
        SysUser sysUser = sysUserService.getByUsername(username);
        UserInfo userInfo = new UserInfo(sysUser.getId(),
                sysUser.getUsername(),
                sysUser.getPassword(),
                sysUser.getStatus());
        List<SysRole> sysRoles = sysRoleService.getByUserId(sysUser.getId());

        return null;

    }
}
