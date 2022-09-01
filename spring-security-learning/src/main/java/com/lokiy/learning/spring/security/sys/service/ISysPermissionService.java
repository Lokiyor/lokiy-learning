package com.lokiy.learning.spring.security.sys.service;

import com.lokiy.learning.spring.security.sys.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lokiy.learning.spring.security.sys.entity.SysRolePermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lokiy
 * @since 2022-08-30
 */
public interface ISysPermissionService extends IService<SysPermission> {

    List<SysPermission> getByRoleIds(List<String> roleIds);
}
