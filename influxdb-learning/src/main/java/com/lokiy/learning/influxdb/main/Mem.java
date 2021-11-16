package com.lokiy.learning.influxdb.main;

import com.influxdb.annotations.Measurement;
import lombok.Data;

import java.time.Instant;


/**
 * @author Lokiy
 * @date 2021/11/11
 * @description 测试对象
 **/
@Data
@Measurement(name = "mem")
public class Mem {

    public String host;

    public Number used_percent;

    public Instant time;
}
