package com.lokiy.learning.spring.security.config;

import com.lokiy.learning.spring.security.util.SnowFlakeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lokiy
 * @date 2022/5/23
 * @description 雪花算法配置
 */
@Configuration
public class SnowFlakeConfig {

    @Bean
    public SnowFlakeUtil snowFlakeUtil(@Value("${ldc.snow-flake-id:1}") long snowFlakeId){
        return new SnowFlakeUtil(snowFlakeId);
    }
}
