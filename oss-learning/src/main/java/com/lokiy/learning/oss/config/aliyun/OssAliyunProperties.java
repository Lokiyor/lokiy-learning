package com.lokiy.learning.oss.config.aliyun;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lokiy
 * @date 2021/12/29
 * @description 阿里云oss配置
 */
@Data
@Configuration
@ConfigurationProperties("oss.aliyun")
public class OssAliyunProperties {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;
}
