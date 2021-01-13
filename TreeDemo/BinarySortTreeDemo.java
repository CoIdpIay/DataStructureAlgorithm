package TreeDemo.BinarySortTree;

/**
 * @author Master
 * @create 2021-01-10-20:00
 * @description 二叉排序树
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree bst = new BinarySortTree();
        // 循环arr创建
        for (int num : arr) {
            bst.createBinarySortTree(new Node(num));
        }
        bst.infixOrder();
    }
}

class BinarySortTree {
    private Node root;

    public void createBinarySortTree(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("当前的二叉排序树是空的无法遍历");
            return;
        }
        root.infixOrder();
    }
}

class Node {
    private int value;
    private Node left;
    private Node right;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node(int value) {
        this.value = value;
    }
    // 查找结点

    /**
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点, 否则返回null
     */
    public Node search(int value) {
        if (this.value == value) { // 如果当前的value == 要查找的value直接返回
            return this;
        } else if (this.value > value) { // 如果当前value大于要查找的,且左不为空则向左递归
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) { // 如果当前value小于要查找的,且右不为空则向右递归
                return null;
            } else {
                return this.right.search(value);
            }
        }
    }
    // 查找要删除结点的父节点

    /**
     * @param value 要找到的结点的值
     * @return
     */
    public Node searchParent(int value) {
        // 如果当前的结点就是要删除的父节点,就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前结点的值,并且当前结点的左子节点不为空
            if (this.left.value < value && this.left != null) {
                return this.left.searchParent(value); // 向左子树递归
            } else if (this.right.value >= value && this.right != null) {
                return this.right.searchParent(value); // 向右子树递归查找
            } else {
                return null; // 没有找到父节点
            }
        }
    }

    // 按照二叉排序树的方式添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.getValue() < this.getValue()) {
            if (this.left == null) {
                this.setLeft(node);
            } else {
                this.left.add(node);
            }
        } else if (node.getValue() >= this.getValue()) {
            if (this.right == null) {
                this.setRight(node);
            } else {
                this.right.add(node);
            }
        }
    }

    // 中序遍历二叉树
    public void infixOrder() {
        if (this.getLeft() != null) {
            this.getLeft().infixOrder();
        }
        System.out.println(this);
        if (this.getRight() != null) {
            this.getRight().infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}