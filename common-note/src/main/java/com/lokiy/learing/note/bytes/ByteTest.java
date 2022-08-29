package com.lokiy.learing.note.bytes;

import cn.hutool.core.util.ByteUtil;

import java.util.Arrays;

/**
 * @author lokiy
 * @date 2022/7/26
 * @description TODO
 */
public class ByteTest {


    public static void main(String[] args) {
        int head = 0xFE;
        byte[] bytes = ByteUtil.intToBytes(head);
        System.out.println(bytes);
    }
}
