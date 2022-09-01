package com.lokiy.learning.spring.security.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.lokiy.learning.spring.security.sys.entity.SysPermission;
import com.lokiy.learning.spring.security.sys.entity.SysRolePermission;
import com.lokiy.learning.spring.security.sys.mapper.SysPermissionMapper;
import com.lokiy.learning.spring.security.sys.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lokiy.learning.spring.security.sys.service.ISysRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lokiy
 * @since 2022-08-30
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {


    @Resource
    private ISysRolePermissionService sysRolePermissionService;

    @Override
    public List<SysPermission> getByRoleIds(List<String> roleIds) {
        if(CollectionUtil.isEmpty(roleIds)){
            return Lists.newArrayList();
        }
        List<SysRolePermission> sysRolePermissions = sysRolePermissionService.list(new LambdaQueryWrapper<SysRolePermission>().in(SysRolePermission::getRoleId, roleIds));
        List<String> permissionIds = sysRolePermissions.stream()
                .map(SysRolePermission::getPermissionId)
                .collect(Collectors.toList());
        if(CollectionUtil.isEmpty(permissionIds)){
            return Lists.newArrayList();
        }
        return this.list(new LambdaQueryWrapper<SysPermission>().in(SysPermission::getId, permissionIds));
    }
}
