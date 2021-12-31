package com.lokiy.learning.oss.controller;

import com.lokiy.learning.oss.util.OssMinioUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lokiy
 * @date 2021/12/29
 * @description 七牛oss测试控制类
 */
@RestController
@RequestMapping("/oss/minio")
public class OssMinioController {


    @PostMapping("/test")
    public String test(@RequestParam("file") MultipartFile file){
        return OssMinioUtil.upload("lokiy-oss", "/test/", file);
    }
}
