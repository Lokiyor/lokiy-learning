package com.lokiy.learning.oss.util;

import cn.hutool.json.JSONUtil;
import com.lokiy.learning.oss.config.OssQiniuProperties;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author lokiy
 * @date 2021/12/29
 * @description 七牛云oss对象存储帮助类
 */
@Component
public class OssQiniuUtil {

    private static OssQiniuProperties ossQiniuProperties;
    private static UploadManager uploadManager;

    @Autowired
    public void setOssQiniuProperties(OssQiniuProperties ossQiniuProperties) {
        OssQiniuUtil.ossQiniuProperties = ossQiniuProperties;
    }

    @Autowired
    public void setUploadManager(UploadManager uploadManager) {
        OssQiniuUtil.uploadManager = uploadManager;
    }

    private static String token(String bucket){
        Auth auth = Auth.create(ossQiniuProperties.getAccessKey(), ossQiniuProperties.getSecretKey());
        return auth.uploadToken(bucket);
    }


    @SneakyThrows
    public static DefaultPutRet upload(String bucket, MultipartFile file){
        try {
            String token = token(bucket);
            Response response = uploadManager.put(file.getBytes(), null, token);
            return JSONUtil.toBean(response.bodyString(), DefaultPutRet.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
