package com.lokiy.learning.spring.security.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lokiy
 * @date 2022/8/31
 * @description TODO
 */
@Component
@ConfigurationProperties(prefix = "security.jwt")
@Data
public class JwtSettings {

    /**
     * 过期时间
     */
    private Long expirationTime;

    /**
     * token到期延长
     */
    private Long extendTime;

    /**
     * 发行人
     */
    private String issuer;

    /**
     * 签约key
     */
    private String signingKey;
}
