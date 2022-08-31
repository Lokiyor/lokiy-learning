package com.lokiy.learning.spring.security.model;

import lombok.Data;

/**
 * @author lokiy
 * @date 2022/8/31
 * @description TODO
 */
@Data
public class LoginParam {

    /**
     * 用户名
     */
    private String username;

    /**
     * 前端md5后的密码
     */
    private String password;

}
