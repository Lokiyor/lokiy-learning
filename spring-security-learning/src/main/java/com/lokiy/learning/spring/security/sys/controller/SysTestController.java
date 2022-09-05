package com.lokiy.learning.spring.security.sys.controller;

import com.lokiy.learning.common.core.domain.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lokiy
 * @date 2022/9/2
 * @description TODO
 */
@RestController
@RequestMapping("/sys/test")
public class SysTestController {


    @PreAuthorize("@pms.hasPermission('sys:test')")
    @GetMapping("/success")
    public R<String> success(){
        return R.result("success");
    }
}
