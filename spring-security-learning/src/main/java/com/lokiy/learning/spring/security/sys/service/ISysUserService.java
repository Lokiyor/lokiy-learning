package com.lokiy.learning.spring.security.sys.service;

import com.lokiy.learning.spring.security.sys.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author Lokiy
 * @since 2022-08-30
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser getByUsername(String username);
}
