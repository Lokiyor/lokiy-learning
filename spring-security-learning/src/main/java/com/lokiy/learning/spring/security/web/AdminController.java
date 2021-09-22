package com.lokiy.learning.spring.security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Lokiy
 * @Date 2021/9/17 11:34
 * @Description
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/demo")
    public String demo() {
        return "success";
    }
}
