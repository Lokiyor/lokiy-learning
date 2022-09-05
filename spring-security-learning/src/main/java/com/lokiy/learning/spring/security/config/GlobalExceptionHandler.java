package com.lokiy.learning.spring.security.config;

import com.lokiy.learning.common.core.domain.R;
import com.lokiy.learning.common.core.enums.CodeEnum;
import com.lokiy.learning.common.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lokiy
 * @date 2022/9/2
 * @description TODO
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 业务异常
     *
     * @param e 业务异常
     * @return 返回信息
     */
    @ExceptionHandler(BusinessException.class)
    public R<?> businessExceptionHandler(BusinessException e) {
        // 业务级异常返回
        log.error("全局异常------>{}", e.getMessage(), e);

        return R.error(e.getCode() == null? CodeEnum.UNKNOWN_ERROR.getCode():e.getCode(), e.getMessage());
    }


    @ExceptionHandler(AccessDeniedException.class)
    public R<?> businessExceptionHandler(AccessDeniedException e) {
        // 业务级异常返回
        log.error("全局异常------>{}", e.getMessage(), e);

        return R.error(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }


    /**
     * 入参校验异常处理
     *
     * @param e 入参校验异常
     * @return 返回信息
     */
    @ExceptionHandler(Exception.class)
    public R<?> exceptionHandler(Exception e) {
        // 未知异常返回
        log.error("全局异常------>{}", e.getMessage(), e);
        return R.error(CodeEnum.UNKNOWN_ERROR.getCode(), CodeEnum.UNKNOWN_ERROR.getMsg());
    }

}
