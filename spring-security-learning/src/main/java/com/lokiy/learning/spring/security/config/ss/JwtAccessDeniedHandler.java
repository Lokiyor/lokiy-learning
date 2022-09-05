package com.lokiy.learning.spring.security.config.ss;

import com.lokiy.learning.common.core.domain.R;
import com.lokiy.learning.common.core.exception.BusinessException;
import com.lokiy.learning.spring.security.util.WebUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lokiy
 * @date 2022/9/1
 * @description TODO
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)  {

        WebUtil.renderResult(response, R.error(new BusinessException(HttpServletResponse.SC_FORBIDDEN, "权限不足")));
    }
}
