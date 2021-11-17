package com.lokiy.learning.influxdb.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Lokiy
 * @date 2021/11/17
 * @description influxdb配置
 **/
@Data
@ConfigurationProperties(prefix = "spring.influx")
@Component
public class InfluxdbConfig {

    private String url;
    private String user;
    private String password;
    private String database;
    private String retentionPolicy;

    @Bean
    public InfluxDBClient init() {
        return InfluxDBClientFactory.createV1(url,user,password.toCharArray() ,database, retentionPolicy);
    }
}
