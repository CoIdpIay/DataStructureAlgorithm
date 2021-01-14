package TreeDemo.BinarySortTree;

import java.lang.annotation.Target;

/**
 * @author Master
 * @create 2021-01-10-20:00
 * @description 二叉排序树
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2, 0};
        BinarySortTree bst = new BinarySortTree();
        // 循环arr创建
        for (int num : arr) {
            bst.createBinarySortTree(new Node(num));
        }
        bst.infixOrder();
//        // 测试删除叶子节点
//        System.out.println("删除后的树");
//        bst.delNode(2);
//        bst.infixOrder();
//        // 测试删除只有一颗子树的节点
//        System.out.println("删除后的树");
//        bst.delNode(1);
//        bst.infixOrder();
        // 测试删除有两颗子树的节点
        System.out.println("删除后的树");
        bst.delNode(3);
        bst.infixOrder();
    }
}

class BinarySortTree {
    private Node root;

    /**
     * @param node
     * @function 创建二叉排序树
     */
    public void createBinarySortTree(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * @function 中序遍历
     */
    public void infixOrder() {
        if (root == null) {
            System.out.println("当前的二叉排序树是空的无法遍历");
            return;
        }
        root.infixOrder();
    }

    /**
     * @param value 想要查找的结点的值
     * @return 想要查找的结点
     * @function 目标节点的查找
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    /**
     * @param value 目标结点的值
     * @return 目标结点的父节点
     * @function 查找目标结点的父节点
     */
    public Node searchParentNode(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    /**
     * @function 查找右子树中最小的值
     * @param tarNode 右子树的根节点
     * @return 返回的是右子树最小的值
     */
    private int delRightMinValue(Node tarNode){
        while(tarNode.getLeft() != null){
            tarNode = tarNode.getLeft();
        }
        // 到这里tarNde就指向了最小节点
        delNode(tarNode.getValue());
        return  tarNode.getValue();
    }

    /**
     * @param value 需要删除的结点的值
     * @function 删除结点
     */
    public void delNode(int value) {
        // 1.找到要删除的targetNode
        // 如果root等于空,则直接返回
        if (root == null) {
            return;
        }
        Node targetNode = root.search(value);
        // 如果没有找到
        if (targetNode == null) {
            return;
        }
        // 如果找到了且这棵树只有根节点
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
            return;
        }
        // 2.找到要删除的targetNode的父节点parentNode
        Node parentNode = root.searchParent(value);
        // 如果要删除的结点是叶子节点
        if (targetNode.getLeft() == null && targetNode.getRight() == null) {
            // 判断targetNode是parentNode的左子节点还是右子节点
            if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == targetNode.getValue()) {
                parentNode.setLeft(null);
            }
            if (parentNode.getRight() != null && parentNode.getRight().getValue() == targetNode.getValue()) {
                parentNode.setRight(null);
            }
        } else if (targetNode.getLeft() != null && targetNode.getRight() != null) {
            // 如果要删除的targetNode是有两棵子树的节点
            if (targetNode.getRight() != null) {
                int minValue = delRightMinValue(targetNode.getRight());
                targetNode.setValue(minValue);
            }
        } else {
            // 如果要删除的targetNode只有一颗子树
            // 如果targetNode只有左子树
            if (targetNode.getLeft() != null) {
                if (parentNode != null) {
                    // 如果targetNode是parentNode的左子节点
                    if (parentNode.getLeft().getValue() == targetNode.getValue()) {
                        parentNode.setLeft(targetNode.getLeft());
                    } else {
                        // 如果targetNode是parentNode的右子节点
                        parentNode.setRight(targetNode.getLeft());
                    }
                } else {
                    // 如果parentNode为空意味着要删除的是根节点,此时直接将根节点设置成子树
                    root = targetNode.getLeft();
                }
            } else { // 如果targetNode只有右子节点
                // 如果targetNode是parentNode的左子节点
                if (parentNode == null) {
                    if (parentNode.getLeft().getValue() == targetNode.getValue()) {
                        parentNode.setLeft(targetNode.getRight());
                    } else {
                        // 如果targetNode是parentNode的右子节点
                        parentNode.setRight(targetNode.getRight());
                    }
                } else {
                    // 如果parentNode为空意味着要删除的是根节点,此时直接将根节点设置成子树
                    root = targetNode.getRight();
                }
            }
        }
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
        // 如果当前的结点就是要目标节点的父节点,就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前结点的值,并且当前结点的左子节点不为空
            if (this.left != null && this.value > value) {
                return this.left.searchParent(value); // 向左子树递归
            } else if (this.right != null && this.value <= value) {
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