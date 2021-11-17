package com.lokiy.learning.influxdb.web;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;

/**
 * @author Lokiy
 * @date 2021/11/17
 * @description 测试接口
 **/
@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    private InfluxDBClient client;

    @GetMapping("/insert")
    public String test(){
        Point point = Point
                .measurement("mem")
                .addTag("host", "host1")
                .addField("used_percent", 23.43234543)
                .time(Instant.now(), WritePrecision.NS);

        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writePoint(point);
        }
        return "success";
    }
}
