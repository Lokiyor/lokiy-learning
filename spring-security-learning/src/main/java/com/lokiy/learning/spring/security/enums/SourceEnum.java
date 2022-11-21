package com.lokiy.learning.spring.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lokiy
 * @date 2022/11/21
 * @description 登录来源
 */
@Getter
@AllArgsConstructor
public enum SourceEnum {
    /**
     *
     */
    SYSTEM("1"),

    APP("2"),

    WECHAT("3"),
    ;

    private final String source;
}
