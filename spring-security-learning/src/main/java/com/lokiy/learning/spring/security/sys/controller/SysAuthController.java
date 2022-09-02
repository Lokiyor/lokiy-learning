package com.lokiy.learning.spring.security.sys.controller;

import com.lokiy.learning.common.core.domain.R;
import com.lokiy.learning.spring.security.model.LoginParam;
import com.lokiy.learning.spring.security.sys.service.SysAuthService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lokiy
 * @date 2022/8/31
 * @description TODO
 */
@RestController
@RequestMapping("/sys/auth")
public class SysAuthController {

    @Resource
    private SysAuthService sysAuthService;

    @PostMapping("/login")
    public R<String> login(@RequestBody LoginParam loginParam){
        String token = sysAuthService.login(loginParam.getUsername(), loginParam.getPassword());
        return R.result(token);
    }


    @GetMapping("/logout")
    public R<Object> logout(){

        return R.success();
    }
}
