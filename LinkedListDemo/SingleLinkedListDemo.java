package LinkedListDemo;

import javax.annotation.processing.SupportedSourceVersion;

/**
 * @author Master
 * @create 2020-12-02-21:19
 * @description 通过单链表的实现对英雄的管理
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLindedList singleLindedList = new SingleLindedList();
        // 测试添加
        singleLindedList.add(new HeroNode("宋江", "及时雨", 1));
        singleLindedList.add(new HeroNode("林冲", "豹子头", 4));
        singleLindedList.add(new HeroNode("林冲", "豹子头", 5));
        singleLindedList.add(new HeroNode("吴用", "智多星", 3));
        singleLindedList.add(new HeroNode("卢俊义", "玉麒麟", 2));

        // 测试更新
        singleLindedList.update(new HeroNode("小卢", "小玉", 2));
        // 测试删除
        singleLindedList.delete(5);
        // 测试显示
        singleLindedList.list();
        // 节点个数查询
        singleLindedList.getNum();
        // 获取倒数第1个节点的英雄的信息
        singleLindedList.getKHernode(1);
        // 反转链表
        singleLindedList.reverseList();
        singleLindedList.list();
    }
}

// 定义SingleLinkedList来管理英雄
class SingleLindedList {
    // 1.先初始化一个头节点,头节点不能动,不存放具体的数据
    private HeroNode head = new HeroNode("头节点", "头节点", 0);

    // 2.按照顺序添加节点到单向链表
    public void add(HeroNode h) {
        HeroNode temp1 = head;  // 辅助变量用于遍历

        while (true) {
            // 如果下一个节点为空,直接将在后面添加该节点
            if (temp1.next == null) {
                temp1.next = h;
                break;
            }
            // 如果下一个节点不为空,比较下一个节点和添加节点的大小
            // 如果下一个节点比添加的节点大,则将前一个节点的next指向添加的节点,并将其next指向后一个节点
            if (temp1.next.getNo() > h.getNo()) {
                h.next = temp1.next;
                temp1.next = h;
                break;
            }
            if (temp1.next.getNo() == h.getNo()) {
                System.out.printf("出错!准备添加的英雄编号%d已经存在\n", h.getNo());
                break;
            }
            // 如果下一个节点比添加的节点大,则将temp向后移一位继续比较
            temp1 = temp1.next;
        }
    }

    // 3.修改节点的信息,编号不能变,根据新的节点直接覆盖旧的节点
    public void update(HeroNode hn) {

        HeroNode temp2 = head;
        boolean flag;

        if (temp2.next == null) {
            System.out.println("链表为空");
            return;
        }

        while (true) {
            if (temp2.next == null) {
                System.out.println("遍历已经结束");
                System.out.println();
            }
            if (temp2.getNo() == hn.getNo()) {
                temp2.name = hn.name;
                temp2.nickName = hn.nickName;
                flag = true;
                break;
            }
            temp2 = temp2.next;
        }

        if (flag) {
            System.out.printf("更新完成,更新的英雄编号为%d\n", hn.getNo());
            System.out.println();
        } else {
            System.out.println("并未找到相同编号的英雄");
            System.out.println();
        }
    }

    // 4.删除节点
    public void delete(int no) {
        HeroNode temp3 = head;
        boolean flag = false;  // 是否找到待删除的节点

        if (temp3.next == null) {
            System.out.println("列表为空");
            return;
        }

        while (true) {
            if (temp3.next == null) {
                System.out.println("遍历已经完成");
                System.out.println();
                break;
            }
            if (temp3.next.getNo() == no) {
                // 判断删除节点是否是最后一个节点
                // 是最后一个节点
//                if(temp3.next.next == null){
//                    temp3.next = null;
//                    flag = true;
//                    System.out.println("删除成功");
//                    break;
//                }
                // 不是最后一个节点
                temp3.next = temp3.next.next;  // 不需要判断,即使删除的节点是最后一个节点,此处的赋值也是null吗?
                flag = true;
                System.out.println("删除成功");
                break;
            }
            temp3 = temp3.next;
        }

        if (flag) {
            System.out.printf("删除的编号为%d", no);
            System.out.println();
        } else {
            System.out.printf("没有编号为%d的英雄\n", no);
            System.out.println();
        }
    }

    // 5.显示链表
    public void list() {
        HeroNode temp4 = head;
        while (temp4.next != null) {
            System.out.println(temp4);
            temp4 = temp4.next;
        }
        System.out.println(temp4);
    }

    // 6.获取链表中节点的个数
    public int getNum() {
        // 创建一个临时的节点用于遍历
        HeroNode temp5 = head;
        int num = 0;
        if (temp5.next == null) {
            System.out.println("链表为空");
            return 0;
        }

        while (temp5.next != null) {
            num++;
            temp5 = temp5.next;
        }
        System.out.printf("节点的数量一共是%d个\n", num);
        return num;
    }

    // 7.倒数第k个节点的属性获取
    public HeroNode getKHernode(int k) {
        int length = this.getNum();
        HeroNode temp5 = head.next;

        if (temp5 == null) {
            System.out.println("链表为空");
            return null;
        }
        if (length < k || k < 0) {
            System.out.println("超出范围无法找到");
            return null;
        }

        for (int i = 0; i < length - k; i++) {
            temp5 = temp5.next;
        }

        System.out.printf("倒数第%d个节点的信息为:", k);
        System.out.println(temp5.toString());
        return temp5;

    }

    // 8.反转链表
    public void reverseList() {

        if (head.next == null || head.next.next == null) {
            return;
        }

        HeroNode cur = head.next;
        HeroNode next;
        HeroNode reverseHead = new HeroNode("", "", 0);

        while (cur != null) {
            next = cur.next;
            // 头节点的next给cur的next
            cur.next = reverseHead.next;
            // cur给头节点的next
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }
}

// 定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode {
    public String name;
    public String nickName;
    private int no;
    public HeroNode next;  // 默认值为null,指向下一个英雄节点

    public HeroNode(String name, String nickName, int no) {
        this.name = name;
        this.nickName = nickName;
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", no=" + no +
                '}';
    }
}

