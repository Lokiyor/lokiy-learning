package com.lokiy.learning.spring.security.sys.service.impl;

import com.google.common.collect.Sets;
import com.lokiy.learning.common.core.exception.BusinessException;
import com.lokiy.learning.spring.security.model.LoginUser;
import com.lokiy.learning.spring.security.model.SysGrantedAuthority;
import com.lokiy.learning.spring.security.model.UserInfo;
import com.lokiy.learning.spring.security.sys.entity.SysPermission;
import com.lokiy.learning.spring.security.sys.entity.SysRole;
import com.lokiy.learning.spring.security.sys.entity.SysUser;
import com.lokiy.learning.spring.security.sys.service.ISysPermissionService;
import com.lokiy.learning.spring.security.sys.service.ISysRoleService;
import com.lokiy.learning.spring.security.sys.service.ISysUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lokiy
 * @date 2022/9/1
 * @description TODO
 */
@Service
public class SysUserDetailsService implements UserDetailsService {

    @Resource
    private ISysUserService sysUserService;

    @Resource
    private ISysPermissionService sysPermissionService;

    @Resource
    private ISysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUsername(username);
        if(sysUser == null){
            throw new BusinessException("用户不存在");
        }
        UserInfo userInfo = new UserInfo(sysUser.getId(),
                sysUser.getUsername(),
                sysUser.getPassword(),
                sysUser.getStatus());
        List<String> roleIds = sysRoleService.getRoleIds(sysUser.getId());
        List<SysRole> sysRoles = sysRoleService.getRoleByIds(roleIds);
        List<SysGrantedAuthority> authorities = sysRoles.stream()
                .map(role -> new SysGrantedAuthority(role.getRoleCode()))
                .collect(Collectors.toList());
        List<SysPermission> permissionList = sysPermissionService.getByRoleIds(roleIds);
        List<String> permissions = permissionList.stream().map(SysPermission::getCode).collect(Collectors.toList());
        return new LoginUser(userInfo, sysUser.getTenantId(), authorities, Sets.newHashSet(permissions));

    }
}
