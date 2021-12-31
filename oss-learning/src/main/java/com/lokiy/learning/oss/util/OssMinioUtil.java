package com.lokiy.learning.oss.util;

import com.lokiy.learning.oss.config.minio.OssMinioProperties;
import io.minio.*;
import io.minio.errors.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author lokiy
 * @date 2021/12/31
 * @description oss minio帮助类
 */
@Component
public class OssMinioUtil {

    private static OssMinioProperties ossMinioProperties;

    @Autowired
    public void setOssMinioProperties(OssMinioProperties ossMinioProperties) {
        OssMinioUtil.ossMinioProperties = ossMinioProperties;
    }

    private static MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(ossMinioProperties.getEndpoint())
                .credentials(ossMinioProperties.getAccessKey(), ossMinioProperties.getSecretKey())
                .build();
    }

    private static void initBucket(MinioClient minioClient, String bucket) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if(!found){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }


    @SneakyThrows
    public static String upload(String bucket, String path, MultipartFile file){
        MinioClient minioClient = minioClient();
        initBucket(minioClient, bucket);
        PutObjectArgs putObject = PutObjectArgs.builder()
                .bucket(bucket)
                .object(path + file.getOriginalFilename())
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        ObjectWriteResponse resp = minioClient.putObject(putObject);
        return resp.object();
    }

}
