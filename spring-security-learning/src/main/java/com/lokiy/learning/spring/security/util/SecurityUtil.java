package com.lokiy.learning.spring.security.util;

import com.lokiy.learning.common.core.exception.BusinessException;
import com.lokiy.learning.spring.security.model.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author lokiy
 * @date 2022/9/5
 * @description TODO
 */
public class SecurityUtil {



    public static LoginUser getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new BusinessException("用户未认证");
        }
        Object principal = authentication.getPrincipal();
        if(principal == null){
            throw new BusinessException("用户未认证");
        }
        return (LoginUser) principal;
    }
}
