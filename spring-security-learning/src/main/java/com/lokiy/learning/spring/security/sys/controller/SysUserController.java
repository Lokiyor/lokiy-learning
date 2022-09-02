package com.lokiy.learning.spring.security.sys.controller;

import com.lokiy.learning.common.core.domain.R;
import com.lokiy.learning.spring.security.sys.entity.SysUser;
import com.lokiy.learning.spring.security.sys.service.ISysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author Lokiy
 * @since 2022-08-30
 */
@RestController
@RequestMapping("/sys/sysUser")
public class SysUserController {

    @Resource
    private ISysUserService sysUserService;


    @PostMapping("/add")
    public R<Object> add(@RequestBody SysUser sysUser){
        sysUserService.add(sysUser);
        return R.success();
    }
}
