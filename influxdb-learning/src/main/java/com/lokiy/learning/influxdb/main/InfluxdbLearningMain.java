package com.lokiy.learning.influxdb.main;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;

import java.time.Instant;

/**
 * @author Lokiy
 * @date 2021/11/11
 * @description 测试主函数
 **/
public class InfluxdbLearningMain {

    public static void main(String[] args) {
        // You can generate a Token from the "Tokens Tab" in the UI
        String token = "bqpZU5XvGEqyFZl481gpXnzUSTYUkht2gV6lwdUjwoJcrnH1ZkgO7xf4YBz1dKJYUCheooQprEmkQADTKjEWVw==";
        String bucket = "lokiy";
        String org = "landleaf";

        InfluxDBClient client = InfluxDBClientFactory.create("http://127.0.0.1:8086", token.toCharArray());
        writeDataPojo(client, bucket, org);

    }

    private static void writeData(InfluxDBClient client, String bucket, String org){
        String data = "mem,host=host1 used_percent=23.43234543";
        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writeRecord(bucket, org, WritePrecision.NS, data);
        }
    }


    private static void writeDataPoint(InfluxDBClient client, String bucket, String org){
        Point point = Point
                .measurement("mem")
                .addTag("host", "host2")
                .addField("used_percent", 23.43234543)
                .time(Instant.now(), WritePrecision.NS);

        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writePoint(bucket, org, point);
        }
    }

    private static void writeDataPojo(InfluxDBClient client, String bucket, String org){
        Mem mem = new Mem();
        mem.host = "host3";
        mem.used_percent = 23.43234543;
        mem.time = Instant.now();

        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writeMeasurement(bucket, org, WritePrecision.NS, mem);
        }
    }

}
