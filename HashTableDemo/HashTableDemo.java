package HashTableDemo;

/**
 * @author Master
 * @create 2020-12-25-17:35
 * @description 哈希表, 头节点不为空
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        hashTable.add(new EmployeeNode(15, "nick"));
        hashTable.add(new EmployeeNode(8, "rose"));
        hashTable.add(new EmployeeNode(1, "tom"));
        hashTable.add(new EmployeeNode(2, "jack"));
        hashTable.add(new EmployeeNode(9, "kitty"));
        hashTable.add(new EmployeeNode(10, "mary"));
        hashTable.add(new EmployeeNode(19, "jerry"));
        hashTable.list();
        EmployeeNode emp = hashTable.findEmp(19);
        System.out.println("查找到的结果是" + emp.toString());
    }
}

class HashTable {
    private HashLinkedList[] linkedListArr;
    int size;

    public HashTable(int size) {
        this.size = size;
        linkedListArr = new HashLinkedList[size];
        for (int i = 0; i < linkedListArr.length; i++) {
            linkedListArr[i] = new HashLinkedList();
        }
    }

    public void add(EmployeeNode emp) {
        int index = hashPosition(emp);
        linkedListArr[index].add(emp);
    }
    
    // 散列函数,此处直接用取模的方式获取散列值
    private int hashPosition(EmployeeNode emp) {
        return emp.id % size;
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            System.out.println();
            System.out.printf("第%d个位置的情况是:", i);
            linkedListArr[i].list();
            System.out.println();
        }
    }

    public EmployeeNode findEmp(int id) {
        HashLinkedList lla = linkedListArr[id % size];
        if (lla.head == null) {
            return null;
        }
        EmployeeNode temp = lla.head;
        while (temp != null) {
            if (temp.id == id) {
                break;
            } else {
                temp = temp.next;
            }
        }
        return temp;
    }
}

class HashLinkedList {
    EmployeeNode head;

    public void add(EmployeeNode emp) {
        // 如果链表为空直接添加
        if (head == null) {
            head = emp;
            return;
        }
        // 如果添加的节点的id小于头节点的id,直接将其变为头节点
        if (emp.id < head.id) {
            emp.next = head;
            head = emp;
            return;
        }
        // 否则往后遍历
        EmployeeNode temp = head;
        while (true) {
            // 当遍历到最后一个时,说明要添加的节点的id在当前链表中最大,直接添加到链表的尾部
            if (temp.next == null) {
                temp.next = emp;
                break;
            } else if (emp.id < temp.next.id) {
                // 一旦出现后一个的id大于要添加的id,说明找到位置
                emp.next = temp.next;
                temp.next = emp;
                break;
            }
            temp = temp.next;
        }
    }

    public void list() {
        if (head == null) {
            return;
        }
        EmployeeNode emp = head;
        while (emp != null) {
            System.out.print(emp.toString());
            emp = emp.next;
        }
    }
}

class EmployeeNode {
    int id;
    String name;
    EmployeeNode next;

    public EmployeeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmployeeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}' + "--->";
    }
}
