package com.lokiy.learning.oss.util;

import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author lokiy
 * @date 2021/12/31
 * @description 简易文件上传帮助类
 */
public class FileUtil {

    public static String write(byte[] bytes, String filePath, String fileName) throws IOException {
        String filePathName = filePath + fileName;
        Path target = Paths.get(filePathName);
        getPath(filePath);
        if(!Files.exists(target)){
            Files.createFile(target);
        }
        Files.write(target, bytes);
        return filePathName;
    }


    private static void getPath(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }



    @SneakyThrows
    public String upload(String path, MultipartFile file){
        return write(file.getBytes(), path, file.getOriginalFilename());
    }
}
