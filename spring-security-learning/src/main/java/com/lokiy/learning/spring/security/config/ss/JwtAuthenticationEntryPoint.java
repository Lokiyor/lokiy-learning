package com.lokiy.learning.spring.security.config.ss;

import com.lokiy.learning.common.core.exception.BusinessException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lokiy
 * @date 2022/9/1
 * @description 身份验证处理器 没有登录直接访问需要权限的接口 (写法类似登录失败的处理器)
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)  {

        throw new BusinessException(HttpServletResponse.SC_UNAUTHORIZED, "用户认证失败请重新登录");

    }
}
