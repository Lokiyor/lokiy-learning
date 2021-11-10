package com.lokiy.learing.note.tree;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Lokiy
 * @date 2021/11/10
 * @description 获取树结构
 **/
public class NodeUtil {

    /**
     * 根节点
     */
    private static final Long ROOT_ID = 0L;


    /**
     * 获得树状节点结构
     * @param all 所有节点
     * @return 树状节点结构
     */
    public static List<Node> trees(List<Node> all){
        return all.stream()
                .filter(m -> ROOT_ID.equals(m.getParentId()))
                .peek(m -> m.setChildren(getChildren(m, all)))
                .collect(Collectors.toList());
    }


    /**
     * 递归获取树状结构节点
     * @param root 根节点
     * @param all 所有集合
     * @return 子节点集合
     */
    private static List<Node> getChildren(Node root, List<Node> all){
        return all.stream()
                .filter(m -> Objects.equals(m.getParentId(), root.getId()))
                .peek(m -> m.setChildren(getChildren(m, all)))
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        List<Node> all = Arrays.asList(
                new Node(1L,"根节点",0L),
                new Node(2L,"子节点1",1L),
                new Node(3L,"子节点1.1",2L),
                new Node(4L,"子节点1.2",2L),
                new Node(5L,"根节点1.3",2L),
                new Node(6L,"根节点2",1L),
                new Node(7L,"根节点2.1",6L),
                new Node(8L,"根节点2.2",6L),
                new Node(9L,"根节点2.2.1",7L),
                new Node(10L,"根节点2.2.2",7L),
                new Node(11L,"根节点3",1L),
                new Node(12L,"根节点3.1",11L)
        );

    }
}
