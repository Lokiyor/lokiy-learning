package com.lokiy.learning.spring.security.sys.service.impl;

import com.lokiy.learning.spring.security.sys.entity.SysUser;
import com.lokiy.learning.spring.security.sys.mapper.SysUserMapper;
import com.lokiy.learning.spring.security.sys.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Lokiy
 * @since 2022-08-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
