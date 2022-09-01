package com.lokiy.learning.spring.security.util;

import com.lokiy.learning.spring.security.model.LoginUser;
import com.lokiy.learning.spring.security.props.JwtSettings;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
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
@Slf4j
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

    public String createToken(String subject){

        ZonedDateTime currentTime = ZonedDateTime.now();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(subject)
                .setIssuer(settings.getIssuer())
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime.plusSeconds(settings.getExpirationTime()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getSigningKey());
        return jwtBuilder.compact();
    }


    public Claims parseToken(String token){
        return parseTokenClaims(token);
    }

    public String getSubject(String token){
        return parseToken(token).getSubject();
    }


    public boolean isTokenExpired(Claims claims){
        return claims.getExpiration().before(new Date());
    }


    @Deprecated
    public String createAccessToken(LoginUser loginUser) {
        //Claims
        List<String> scopes = loginUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        Claims claims = Jwts.claims().setSubject(loginUser.getUsername());
        claims.put(USER_ID, loginUser.getUserInfo().getUserId());
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
                .signWith(SignatureAlgorithm.HS512, settings.getSigningKey());
        return jwtBuilder.compact();
    }

    public Claims parseTokenClaims(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(settings.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            log.debug("Invalid JWT Token", ex);
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (ExpiredJwtException expiredEx) {
            log.debug("JWT Token is expired", expiredEx);
            throw new CredentialsExpiredException(token + "| JWT Token expired", expiredEx);
        }

    }

    @Deprecated
    public String createRefreshToken(LoginUser loginUser){
        return null;
    }
}
