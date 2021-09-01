package com.lokiy.learning.influxdb.main;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;

import java.time.Instant;

/**
 * @Author Lokiy
 * @Date 2021/9/1 11:39
 * @Description 测试主函数
 */
public class InfluxdbMain {

    public static void main(String[] args) {
        String token = "v8o3SquwEnwbJfQgyN-dOYlHesc8IRBEgBmQ88vwN4uwULa6M_hFLBBno2slafq21sSSWesCThACubsuKD9Lig==";
        String bucket = "lokiy";
        String org = "lokiy";
        InfluxDBClient client = InfluxDBClientFactory.create("http://127.0.0.1:8086", token.toCharArray());

        lineProtocol(client, bucket, org);

    }

    private static void lineProtocol(InfluxDBClient client, String bucket, String org){
        String data = "mem,host=host1 used_percent=23.43234543";
        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writeRecord(bucket, org, WritePrecision.NS, data);
        }
    }

    private static void dataPoint(InfluxDBClient client, String bucket, String org){
        Point point = Point
                .measurement("mem")
                .addTag("host", "host1")
                .addField("used_percent", 23.43234543)
                .time(Instant.now(), WritePrecision.NS);

        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writePoint(bucket, org, point);
        }
    }
}
