package com.lokiy.learning.spring.security.constant;

/**
 * @author lokiy
 * @date 2022/9/1
 * @description TODO
 */
public interface RedisKeyConst {

    /**
     * 存储用户token的key
     */
    String USER_TOKEN_KEY = "user_token:%s:%s";

    String LOGIN_USER_KEY = "login:%s:%s";
}
