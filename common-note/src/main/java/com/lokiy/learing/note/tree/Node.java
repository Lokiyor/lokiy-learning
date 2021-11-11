package com.lokiy.learing.note.tree;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Lokiy
 * @date 2021/11/10
 * @description 节点
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Node {
    /**
     * 主键id
     */
    public Long id;

    /**
     * 名称
     */
    public String name;

    /**
     * 父id ，根节点为0
     */
    public Long parentId;

    /**
     * 子节点信息
     */
    public List<Node> children;

    public Node(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }
}
