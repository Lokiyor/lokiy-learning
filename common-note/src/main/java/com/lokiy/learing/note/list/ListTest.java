package com.lokiy.learing.note.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lokiy
 * @date 2021/11/11
 * @description 测试list操作
 **/
public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        list.set(0, "0");
        System.out.println(list);
    }
}
