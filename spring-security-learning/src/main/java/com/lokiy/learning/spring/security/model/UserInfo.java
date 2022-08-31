package com.lokiy.learning.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lokiy
 * @date 2022/8/31
 * @description TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String userId;
    private String username;
    private String password;
    private Integer status;
}
