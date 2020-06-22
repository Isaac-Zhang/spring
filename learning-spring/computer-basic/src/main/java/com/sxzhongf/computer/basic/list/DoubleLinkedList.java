package com.sxzhongf.computer.basic.list;

import java.util.Objects;

/**
 * 双向链表实现
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/22
 **/
public class DoubleLinkedList {

    // 链表容量
    private Integer capacity = 8;
    // 链表元素个数
    private Integer size = 0;
    // 头部节点
    private Node head = null;
    // 尾部节点
    private Node tail = null;

    public DoubleLinkedList(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * 添加节点
     *
     * @param node 传入的节点
     * @return 节点信息
     */
    public Node offer(Node node) {
        return add(node);
    }

    public Node add(Node node) {
        // 如何当前对象的头部节点为null 或者 size为0 ，那么说明当前添加的是第一个节点
        if (Objects.isNull(this.head) || this.size == 0) {
            this.head = node;
            this.tail = node;
        } else {
            //1. 把当前的head设置为 传入node 的 下一个节点
            node.next = this.head;
            //2. 把当下的head的prev设置为当前传入的node
            this.head.prev = node;
            //3. 把传入的节点设置为head节点
            this.head = node;
        }
        this.size += 1;

        return node;
    }

    @Override
    public String toString() {
        return "DoubleLinkedList{" +
                "capacity=" + capacity +
                ", size=" + size +
                '}';
    }

    /**
     * 链表元素信息
     */
    public static class Node {

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }

        private String key;
        private String value;
        // 上一个元素引用
        private Node prev;
        // 下一个元素引用
        private Node next;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    ", prev=" + prev +
                    ", next=" + next +
                    '}';
        }
    }
}
