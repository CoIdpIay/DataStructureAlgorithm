package LinkedListDemo;

/**
 * @author Master
 * @create 2020-12-07-19:34
 * @description 双链表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        doubleLinkedList dll = new doubleLinkedList();
        // 测试添加
        dll.add(new doubleHeroNode("崔斯特", "卡牌大师", 1));
        dll.add(new doubleHeroNode("烬", "戏命师", 5));
        dll.add(new doubleHeroNode("艾希", "寒冰射手", 3));
        dll.add(new doubleHeroNode("卢锡安", "奥巴马", 4));
        dll.add(new doubleHeroNode("易", "无极剑圣", 2));
        // 测试打印
        dll.list();
        // 测试删除
        dll.delHero(1);
        dll.list();
        // 测试更新
        dll.upDate(new doubleHeroNode("烬", "拐子", 5));
        dll.list();
        // 测试查找
        dll.findHero(5);
    }
}

class doubleLinkedList {
    doubleHeroNode head = new doubleHeroNode();

    // 按照排名添加
    public void add(doubleHeroNode dhn) {
        doubleHeroNode temp = head;
        while (true) {
            // 如果节点的下一个为空,遍历到头了,直接在后面添加
            if (temp.next == null) {
                temp.next = dhn;
                dhn.pre = temp;
                System.out.println("添加成功");
                break;
            }
            if (temp.next.no > dhn.no) {
                dhn.pre = temp;
                dhn.next = temp.next;
                temp.next = dhn;
                dhn.next.pre = dhn;
                System.out.println("添加成功");
                break;
            }
            if (temp.no == dhn.no) {
                System.out.println("英雄已存在");
                break;
            }
            temp = temp.next;
        }
    }

    // 删除指定编号的英雄
    public void delHero(int no) {
        doubleHeroNode temp = head;
        boolean isFlag = false;
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        while (temp != null) {
            if (temp.no == no) {
                isFlag = true;
                break;
            }
            temp = temp.next;
        }
        if (isFlag) {
            if (temp.next != null) {
                temp.pre.next = temp.next;
                temp.next.pre = temp.pre;
                System.out.println("删除成功");
                return;
            }
            temp.pre.next = null;
            System.out.println("删除成功");
            return;
        }
        System.out.println("没有找到对应的英雄");
    }

    // 更新指定编号的英雄
    public void upDate(doubleHeroNode dhn) {
        doubleHeroNode temp = head.next;
        boolean isFlag= false;
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            if (temp.no == dhn.no) {
                isFlag = true;
                break;
            }
            temp = temp.next;
        }
        if (isFlag) {
            temp.name = dhn.name;
            temp.nickName = dhn.nickName;
            System.out.println("替换成功");
            return;
        }
        System.out.println("没有这个编号的英雄");
    }

    // 查找指定的英雄
    public void findHero(int no) {
        boolean isFlag = false;
        doubleHeroNode temp = head.next;
        if (temp == null) {
            System.out.println("链表为空");
        }
        while (temp != null) {
            if (temp.no == no) {
                isFlag = true;
                break;
            }
            temp = temp.next;
        }
        if (isFlag) {
            System.out.println("查找成功:");
            System.out.println(temp.toString());
            return;
        }
        System.out.println("没有这个英雄");
    }

    // 展示链表
    public void list() {
        doubleHeroNode temp = head.next;
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }
}

class doubleHeroNode {
    String name;
    String nickName;
    int no;
    doubleHeroNode pre;
    doubleHeroNode next;

    public doubleHeroNode() {
    }

    public doubleHeroNode(String name, String nickName, int no) {
        this.name = name;
        this.nickName = nickName;
        this.no = no;
    }

    @Override
    public String toString() {
        return "[" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", num=" + no +
                ']';
    }
}



