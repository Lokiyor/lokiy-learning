package com.lokiy.learning.spring.security.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.lokiy.learning.spring.security.sys.entity.SysRole;
import com.lokiy.learning.spring.security.sys.entity.SysUserRole;
import com.lokiy.learning.spring.security.sys.mapper.SysRoleMapper;
import com.lokiy.learning.spring.security.sys.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lokiy.learning.spring.security.sys.service.ISysUserRoleService;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private ISysUserRoleService sysUserRoleService;

    @Override
    public List<SysRole> getByUserId(String userId) {
        List<SysUserRole> sysUserRoles = sysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, userId));
        if(CollectionUtil.isEmpty(sysUserRoles)){
            return Lists.newArrayList();
        }
        List<String> roleIds = sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        return this.list(new LambdaQueryWrapper<SysRole>().in(SysRole::getId, roleIds));
    }

    @Override
    public List<String> getRoleIds(String userId) {
        List<SysUserRole> sysUserRoles = sysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, userId));
        return sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
    }

    @Override
    public List<SysRole> getRoleByIds(List<String> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return Lists.newArrayList();
        }
        return this.list(new LambdaQueryWrapper<SysRole>().in(SysRole::getId, ids));
    }
}
