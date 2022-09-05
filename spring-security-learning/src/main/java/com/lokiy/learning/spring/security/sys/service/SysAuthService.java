package com.lokiy.learning.spring.security.sys.service;

import cn.hutool.core.util.StrUtil;
import com.lokiy.learning.common.core.exception.BusinessException;
import com.lokiy.learning.spring.security.constant.CommonConst;
import com.lokiy.learning.spring.security.constant.RedisKeyConst;
import com.lokiy.learning.spring.security.model.LoginUser;
import com.lokiy.learning.spring.security.util.JwtTokenFactory;
import com.lokiy.learning.spring.security.util.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author lokiy
 * @date 2022/8/31
 * @description TODO
 */
@Service
public class SysAuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtTokenFactory jwtTokenFactory;

    @Resource
    private RedisUtil redisUtil;


    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authentication)){
            throw new BusinessException("用户名或密码错误");
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUserInfo().getUserId();
        String token = jwtTokenFactory.createToken(userId);
        redisUtil.set(String.format(RedisKeyConst.LOGIN_USER_KEY, userId), loginUser);
        return token;
    }

    public void logout() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(CommonConst.TOKEN_HEADER);
        if(StrUtil.isBlank(token)){
            return;
        }
        Claims claims = jwtTokenFactory.parseToken(token);
        if(jwtTokenFactory.isTokenExpired(claims)){
            throw new BusinessException(HttpServletResponse.SC_UNAUTHORIZED, "token已过期");
        }
        String userId = claims.getSubject();
        redisUtil.del(String.format(RedisKeyConst.LOGIN_USER_KEY, userId));
    }
}
