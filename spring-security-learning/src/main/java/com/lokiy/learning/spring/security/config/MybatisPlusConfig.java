package com.lokiy.learning.spring.security.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lokiy
 * @date 2022/8/31
 * @description TODO
 */
@Configuration
@MapperScan("com.lokiy.learning.**.mapper*")
public class MybatisPlusConfig {
}
