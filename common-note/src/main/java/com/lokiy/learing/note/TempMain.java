package com.lokiy.learing.note;

import cn.hutool.crypto.digest.MD5;

/**
 * @author lokiy
 * @date 2022/9/2
 * @description TODO
 */
public class TempMain {

    public static void main(String[] args) {
        String s = MD5.create().digestHex("123456");
        System.out.println(s);
    }
}
