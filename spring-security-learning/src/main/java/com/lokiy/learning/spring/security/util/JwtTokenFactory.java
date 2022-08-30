package com.lokiy.learning.spring.security.util;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

/**
 * @author lokiy
 * @date 2022/8/29
 * @description TODO
 */
@Component
public class JwtTokenFactory {


    public String createAccessJwtToken(String userId) {
        Jwts.claims().setSubject(userId);

        return null;

    }

}
