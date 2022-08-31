package com.lokiy.learning.common.core.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Lokiy
 * @date 2019/7/17 11:43
 * @description: error code enum
 */
@AllArgsConstructor
@Getter
public enum CodeEnum {

    /**
     * 成功标识
     */
    SUCCESS(200, "SUCCESS"),

    UNKNOWN_ERROR( 500, "未知错误"),


    ;

    private final Integer code;

    private final String msg;


    public static CodeEnum getErrorCodeEnum(Integer code) {
        for (CodeEnum ce : values()) {
            if (ce.code.equals(code)) {
                return ce;
            }
        }
        return UNKNOWN_ERROR;
    }
}
