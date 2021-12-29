package com.lokiy.learning.oss.config;

import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lokiy
 * @date 2021/12/29
 * @description 七牛云oss配置
 */
@Configuration
public class OssQiniuConfiguration {


    @Bean
    public UploadManager uploadManager(){
        com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration(Region.huadong());
        return new UploadManager(cfg);
    }
}
