package com.sxzhongf.computer.basic.list;

/**
 * 用于测试
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/22
 **/
public class TestClazz {

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList(10);
        doubleLinkedList.offer(new DoubleLinkedList.Node("1","isaac"));
        System.out.println(doubleLinkedList);
        doubleLinkedList.offer(new DoubleLinkedList.Node("2","zp"));
        System.out.println(doubleLinkedList);
        doubleLinkedList.offer(new DoubleLinkedList.Node("3","dy"));
        System.out.println(doubleLinkedList);
    }
}
