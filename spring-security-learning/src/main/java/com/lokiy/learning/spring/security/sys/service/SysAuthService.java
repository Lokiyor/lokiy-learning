package com.lokiy.learning.spring.security.sys.service;

import com.lokiy.learning.common.core.exception.BusinessException;
import com.lokiy.learning.spring.security.constant.RedisKeyConst;
import com.lokiy.learning.spring.security.model.LoginUser;
import com.lokiy.learning.spring.security.util.JwtTokenFactory;
import com.lokiy.learning.spring.security.util.RedisUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new BusinessException("用户名或密码错误");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUserInfo().getUserId();
        String token = jwtTokenFactory.createToken(userId);
        redisUtil.set(String.format(RedisKeyConst.LOGIN_USER_KEY, userId), loginUser);
        return token;
    }

}
