package com.lokiy.learing.note.tree;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
     * 获得树状节点结构 - （递归遍历）
     * @param all 所有节点
     * @return 树状节点结构
     */
    public static List<Node> trees(List<Node> all){
        return all.stream()
                .filter(n -> ROOT_ID.equals(n.getParentId()))
                .peek(n -> n.setChildren(getChildren(n, all)))
                .collect(Collectors.toList());
    }


    /**
     * 递归获取树状结构节点
     * @param node 根节点
     * @param all 所有节点
     * @return 子节点集合
     */
    private static List<Node> getChildren(Node node, List<Node> all){
        return all.stream()
                .filter(n -> Objects.equals(n.getParentId(), node.getId()))
                .peek(n -> n.setChildren(getChildren(n, all)))
                .collect(Collectors.toList());
    }


    /**
     * 树形结构 - （通过临时map归类）
     * @param all 所有节点
     * @return 属性结构
     */
    public static List<Node> treeUseMap(List<Node> all){
        Map<Long, List<Node>> parentNodeMap = all.stream()
                .collect(Collectors.groupingBy(Node::getParentId));
        return all.stream()
                .filter(n -> ROOT_ID.equals(n.getParentId()))
                .peek(n -> integrate(n, parentNodeMap))
                .collect(Collectors.toList());
    }


    /**
     * 归类node子节点
     * @param node node节点
     * @param parentNodeMap 父id map
     */
    private static void integrate(Node node, Map<Long, List<Node>> parentNodeMap){
        List<Node> children = parentNodeMap.get(node.getId());
        if(CollectionUtil.isEmpty(children)){
            return;
        }
        node.setChildren(children);
        children.forEach(n -> integrate(n, parentNodeMap));
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
        long start  = System.currentTimeMillis();
        List<Node> trees = trees(all);
        String s = JSONUtil.toJsonStr(trees);
        System.out.println(s);
        System.out.println(System.currentTimeMillis() - start);
    }
}
