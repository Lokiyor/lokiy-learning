package com.lokiy.learning.spring.security.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.lokiy.learning.spring.security.model.LoginUser;
import com.lokiy.learning.spring.security.props.JwtSettings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lokiy
 * @date 2022/8/29
 * @description TODO
 */
@Component
public class JwtTokenFactory {


    private static final String SCOPES = "scopes";
    private static final String USER_ID = "userId";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ENABLED = "enabled";
    private static final String TENANT_ID = "tenantId";


    private final JwtSettings settings;

    @Autowired
    public JwtTokenFactory(JwtSettings settings) {
        this.settings = settings;
    }


    public String createAccessToken(LoginUser loginUser) {
        //Claims
        List<String> scopes = loginUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        Claims claims = Jwts.claims().setSubject(String.valueOf(loginUser.getUserId()));
        claims.put(USER_ID, loginUser.getUserId());
        claims.put(USERNAME, loginUser.getUsername());
        claims.put(PASSWORD, loginUser.getPassword());
        claims.put(ENABLED, loginUser.isEnabled());
        claims.put(TENANT_ID, loginUser.getTenantId());
        claims.put(SCOPES, scopes);
        //设置jwt
        ZonedDateTime currentTime = ZonedDateTime.now();
        JwtBuilder jwtBuilder = Jwts.builder()
                .addClaims(claims)
                .setIssuer(settings.getIssuer())
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime.plusSeconds(settings.getExpirationTime()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getSignKey());
        return jwtBuilder.compact();
    }


    public String createRefreshToken(LoginUser loginUser){
        return null;
    }
}
