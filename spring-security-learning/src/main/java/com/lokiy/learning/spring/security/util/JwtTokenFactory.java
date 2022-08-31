package com.lokiy.learning.spring.security.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.lokiy.learning.spring.security.model.LoginUser;
import com.lokiy.learning.spring.security.props.JwtSettings;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author lokiy
 * @date 2022/8/29
 * @description TODO
 */
@Component
public class JwtTokenFactory {

    private final JwtSettings settings;

    @Autowired
    public JwtTokenFactory(JwtSettings settings) {
        this.settings = settings;
    }


    public String createAccessJwtToken(LoginUser loginUser) {
        Jwts.claims().setSubject(String.valueOf(loginUser.getUserId()));

        ZonedDateTime currentTime = ZonedDateTime.now();
        Jwts.builder()
                .addClaims(null)
                .setIssuer("")
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime.plusSeconds(settings.getExpirationTime()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getSignKey())
        ;


        return null;

    }

}
