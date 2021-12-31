package com.lokiy.learning.oss.config.qiniu;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lokiy
 * @date 2021/12/29
 * @description 七牛云oss配置
 */
@Data
@Configuration
@ConfigurationProperties("oss.qiniu")
public class OssQiniuProperties {

    private String accessKey;

    private String secretKey;
}
