package com.lokiy.learning.spring.security.config.ss;

import cn.hutool.json.JSONUtil;
import com.lokiy.learning.common.core.domain.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author lokiy
 * @date 2022/9/1
 * @description 身份验证处理器 没有登录直接访问需要权限的接口 (写法类似登录失败的处理器)
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        R<Object> result = R.error(HttpServletResponse.SC_UNAUTHORIZED, "用户认证世白请重新登录");

        response.setContentType("application/json;charset=UTF-8");
        // 403
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
