package com.lokiy.learning.spring.security.util;

import cn.hutool.json.JSONUtil;
import com.lokiy.learning.common.core.domain.R;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author lokiy
 * @date 2022/9/5
 * @description TODO
 */
@Slf4j
public class WebUtil {


    public static void renderResult(HttpServletResponse response, R<?> r){
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(r.getCode());
            String result = JSONUtil.toJsonStr(r);
            ServletOutputStream out = response.getOutputStream();
            out.write(result.getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("返回出错,{}",e.getMessage(), e);
        }
    }
}
