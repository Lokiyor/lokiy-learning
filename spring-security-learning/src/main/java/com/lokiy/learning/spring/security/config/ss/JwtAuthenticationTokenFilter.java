package com.lokiy.learning.spring.security.config.ss;

import cn.hutool.core.util.StrUtil;
import com.lokiy.learning.common.core.exception.BusinessException;
import com.lokiy.learning.spring.security.constant.CommonConst;
import com.lokiy.learning.spring.security.constant.RedisKeyConst;
import com.lokiy.learning.spring.security.model.LoginUser;
import com.lokiy.learning.spring.security.util.JwtTokenFactory;
import com.lokiy.learning.spring.security.util.RedisUtil;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author lokiy
 * @date 2022/9/1
 * @description 接口前置过滤器，用于验证token
 */
@Component
public class JwtAuthenticationTokenFilter extends BasicAuthenticationFilter {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private JwtTokenFactory jwtTokenFactory;

    public JwtAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader(CommonConst.TOKEN_HEADER);
        if(StrUtil.isBlank(token)){
            chain.doFilter(request, response);
            return;
        }
        Claims claims = jwtTokenFactory.parseToken(token);
        if(jwtTokenFactory.isTokenExpired(claims)){
            throw new BusinessException(HttpServletResponse.SC_UNAUTHORIZED, "token已过期");
        }
        String userId = claims.getSubject();

        LoginUser loginUser = (LoginUser) redisUtil.get(String.format(RedisKeyConst.LOGIN_USER_KEY, userId));
        if(Objects.isNull(loginUser)){
            throw new BusinessException(HttpServletResponse.SC_FORBIDDEN, "用户未登录");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }
}
