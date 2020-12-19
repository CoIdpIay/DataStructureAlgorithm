package StackDemo;

import java.awt.*;
import java.security.Signature;

/**
 * @author Master
 * @create 2020-12-14-20:24
 * @description
 */
public class LinkedListStockDemo {
    public static void main(String[] args) {
        LinkedListStock linkedListStock = new LinkedListStock(10);
        for (int i = 1; i < 10; i++) {
            linkedListStock.add(new SingleNode(i));
        }
        for (int i = 1; i < 5; i++) {
            linkedListStock.pop();
        }
        linkedListStock.list();
    }
}

class LinkedListStock {
    private int capacity;

    public LinkedListStock(int capacity) {
        this.capacity = capacity;
    }

    SingleLinkedList s = new SingleLinkedList();

    // 判断栈是否满
    public boolean isFull() {
        if (s.getCounter() >= capacity) {
            System.out.println("栈已经满了");
            return true;
        }
        return false;
    }

    // 判断栈是否空
    public boolean isEmpty() {
        if (s.getCounter() == 0) {
            System.out.println("栈已经空了");
            return true;
        }
        return false;
    }

    // 添加数据进栈
    public void add(SingleNode node) {
        if (isFull() == false) {
            s.add(node);
        }
    }

    // 移除数据出栈
    public SingleNode pop() {
        if (isEmpty() == false) {
            return s.del();
        }
        return null;
    }

    // 显示栈
    public void list() {
        if (isEmpty() == false) {
            s.list();
            return;
        }
        System.out.println("栈是空的,不能打印");
    }
}

// 创建单链表
class SingleLinkedList {
    SingleNode head = new SingleNode();
    SingleNode temp;
    private int counter = 0;

    public SingleLinkedList() {
    }

    public int getCounter() {
        return counter;
    }

    // 实现链表的添加操作,从末尾添加
    public void add(SingleNode node) {
        temp = head;
        // 先找到链表的最后一个节点
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(node);
        counter++;
    }

    // 实现链表的移除操作,移除最后一个
    public SingleNode del() {
        SingleNode f;
        temp = head;
        if (temp.getNext() == null) {
            System.out.println("栈为空");
            return null;
        }
        while (temp.getNext().getNext() != null) {
            temp = temp.getNext();
        }
        f = temp.getNext();
        temp.setNext(null);
        counter--;
        return f;
    }

    // 实现打印链表
    public void list() {
        temp = head.getNext();
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.getNext();
        }
    }
}

// 创建节点
class SingleNode {
    private int data;
    private SingleNode next;

    public SingleNode() {
    }

    public SingleNode(int data) {
        this.data = data;
    }

    public SingleNode getNext() {
        return next;
    }

    public void setNext(SingleNode node) {
        this.next = node;
    }

    @Override
    public String toString() {
        return "SingleNode{" +
                "data=" + data +
                '}';
    }
}
