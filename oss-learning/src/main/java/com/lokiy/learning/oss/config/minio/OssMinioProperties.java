package com.lokiy.learning.oss.config.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lokiy
 * @date 2021/12/31
 * @description minio oss配置类
 */
@Data
@Configuration
@ConfigurationProperties("oss.minio")
public class OssMinioProperties {

    private String endpoint;

    private String accessKey;

    private String secretKey;
}
