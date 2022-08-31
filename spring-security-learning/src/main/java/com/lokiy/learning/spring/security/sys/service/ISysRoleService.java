package com.lokiy.learning.spring.security.sys.service;

import com.lokiy.learning.spring.security.sys.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lokiy
 * @since 2022-08-30
 */
public interface ISysRoleService extends IService<SysRole> {

    List<SysRole> getByUserId(String userId);
}
