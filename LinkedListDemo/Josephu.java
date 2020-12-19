package LinkedListDemo;

import java.awt.font.NumericShaper;

/**
 * @author Master
 * @create 2020-12-08-14:36
 * @description
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList clss = new CircleSingleLinkedList();
        clss.addBoys(5);
        clss.josephuMethod(5, 1, 2);
//        clss.list();
    }
}

// 创建一个环形的单向链表
class CircleSingleLinkedList {
    private Boy first = new Boy(-1);
    private Boy cur;

    // 一次性添加多个节点
    public void addBoys(int num) {
        if (num < 1) {
            System.out.println("添加的数目不合法");
            return;
        }
        cur = first;
        for (int i = 1; i <= num; i++) {
            cur.setNext(new Boy(i));
            cur = cur.getNext();
        }
        cur.setNext(first.getNext());
        first = cur.getNext();
    }

    // 显示链表
    public void list() {
        // 判断链表是否为空
        if (first.getNo() == -1) {
            System.out.println("链表为空");
            return;
        }
        cur = first;
        while (true) {
            System.out.printf("小孩的编号为%d\n", cur.getNo());
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
    }

    /**
     * @param n       孩子的总数量
     * @param startNo 从第startNo个孩子开始数起
     * @param counter 数n下出列
     */
    public void josephuMethod(int n, int startNo, int counter) {
        if (first == null || startNo < 1 || n < counter) {
            System.out.println("参数输入有误,请重新输入");
        }
        // helper节点用于辅助删除节点,初始位置在环形链表的最后一个位置
        Boy helper = first;
        while (true) {
            helper = helper.getNext();
            if (helper.getNext() == first) {
                break;
            }

        }
        // 将first节点和helper节点同时移动第startNo的孩子
        for (int i = 1; i < startNo; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 数n次,并将孩子从链表中剔除并输出,first和helper节点所在位置的孩子也要报数
        while (true) {
            if (first == helper) {
                System.out.println(first.toString());
                break;
            }
            for (int i = 0; i < counter - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println(first.toString());
            first = first.getNext();
            helper.setNext(first);
        }
    }
}

// 创建boy节点的类表示一个节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return this.no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy getNext() {
        return this.next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}