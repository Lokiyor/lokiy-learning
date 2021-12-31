package com.lokiy.learning.oss.util;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.lokiy.learning.oss.config.aliyun.OssAliyunProperties;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lokiy
 * @date 2021/12/31
 * @description 阿里云oss帮助类
 */
@Component
public class OssAliyunUtil {

    private static OssAliyunProperties ossAliyunProperties;

    private static ClientBuilderConfiguration clientBuilderConfiguration;

    @Autowired
    public void setOssAliyunProperties(OssAliyunProperties ossAliyunProperties) {
        OssAliyunUtil.ossAliyunProperties = ossAliyunProperties;
    }

    @Autowired
    public void setClientBuilderConfiguration(ClientBuilderConfiguration clientBuilderConfiguration) {
        OssAliyunUtil.clientBuilderConfiguration = clientBuilderConfiguration;
    }

    private OSS init(){
        return new OSSClientBuilder().build(
                ossAliyunProperties.getEndpoint(),
                ossAliyunProperties.getAccessKeyId(),
                ossAliyunProperties.getAccessKeySecret(),
                clientBuilderConfiguration);
    }

    private void destroy(OSS aliyunOssClient){
        aliyunOssClient.shutdown();
    }


    @SneakyThrows
    public String upload(String bucket, String path, MultipartFile file){
        OSS aliyunOssClient = init();
        String fileName = path + file.getOriginalFilename();
        PutObjectRequest req = new PutObjectRequest(bucket, fileName, file.getInputStream());
        aliyunOssClient.putObject(req);
        destroy(aliyunOssClient);
        return fileName;
    }

}
