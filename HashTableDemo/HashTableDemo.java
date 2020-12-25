package HashTableDemo;

/**
 * @author Master
 * @create 2020-12-25-17:35
 * @description 哈希表
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        hashTable.add(new EmployeeNode(1,"tom"));
        hashTable.add(new EmployeeNode(2,"jack"));
        hashTable.add(new EmployeeNode(19,"jerry"));
        hashTable.list();

        EmployeeNode emp = hashTable.findEmp(19);
        System.out.println(emp.toString());
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

    private int hashPosition(EmployeeNode emp) {
        return emp.id % size;
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            linkedListArr[i].list();
        }
    }

    public EmployeeNode findEmp(int id) {
        HashLinkedList lls = linkedListArr[id % size];
        if (lls.head == null) {
            return null;
        }
        EmployeeNode temp = lls.head;
        while (temp != null) {
            if (temp.id == id) {
                return temp;
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
        if (head == null) {
            head = emp;
            return;
        } else {
            EmployeeNode temp = head;
            while (true) {
                if (temp.next == null) {
                    break;
                } else {
                    temp = temp.next;
                }
            }
            temp.next = emp;
        }
    }

    public void list() {
        if (head == null) {
            return;
        }
        EmployeeNode emp = head;
        while (emp != null) {
            System.out.println(emp.toString());
            emp = emp.next;
        }
    }
}

class EmployeeNode {
    int id;
    String name;
    EmployeeNode next;

    public EmployeeNode() {
    }

    public EmployeeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmployeeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
