package com.lokiy.learning.spring.security.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.lokiy.learning.common.core.domain.R;
import com.lokiy.learning.spring.security.constant.CommonConst;
import com.lokiy.learning.spring.security.enums.SourceEnum;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
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

    /**
     * 获取登录来源信息
     * @return 登录来源
     */
    public static String getSource(){
        HttpServletRequest request = SpringContextUtil.getHttpServletRequest();
        String source = request.getHeader(CommonConst.SOURCE_HEADER);
        if(StrUtil.isBlank(source)){
            source = SourceEnum.SYSTEM.getSource();
        }
        return source;
    }
}
