package TreeDemo.AVLTree;

/**
 * @author Master
 * @create 2021-01-24-15:17
 * @description  AVL树的实现
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.createBinarySortTree(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println("树的高度是:");
        System.out.println(avlTree.getRoot().height());
        System.out.println("左子树的高度是:");
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度是:");
        System.out.println(avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot());
    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * @param
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

    /**
     * @return 返回高度
     * @fuction 计算根节点的左子树的高度
     */
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.height();
    }

    /**
     * @return 返回高度
     * @fuction 计算根节点的右子树的高度
     */
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }

    /**
     * @return
     * @function 返回以当前节点为根节点的树的高度, 树的高度取决于左子树和右子树中较高的值
     */
    public int height() {
        // +1是因为本身自己也要算一层
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }


    // 按照二叉排序树的方式添加节点,平衡二叉树一定是二叉排序树
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
        // 当添加完一个结点吼,如果(右子树高度 - 左子树的高度) > 1 , 需要进行左旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            // 如果当前根节点的右子树的左子树的高度大于当前根节点的右子树的右子树的高度,对这个子树先进行右旋
            if(this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                this.right.rightRotate();
                // 再对根节点进行左旋转
                this.leftRotate();
            }else{
                // 直接进行左旋
                this.leftRotate();
            }
            return;  // 必须要!!避免再向下判断

        }

        // 当添加完一个结点吼,如果(左子树高度 - 右子树的高度) > 1 , 需要进行右旋转
        if (this.leftHeight() - this.rightHeight() > 1) {
            // 如果当前根节点的左子树的右子树高度大于当前根节点的左子树的左子树的高度,对这个子树先进行左旋
            if(this.left !=null && this.left.rightHeight() > this.left.leftHeight()){
                this.left.leftRotate();
            }
            // 再对根节点进行右旋转
            this.rightRotate();
        }else{
            // 直接进行右旋转
            this.rightHeight();
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

    // 左旋转
    private void leftRotate() {
        //1.创建一个新的结点,值等于当前节点的值
        Node newNode = new Node(this.value);
        //2.新的结点的左子树设置为当前节点的左子树
        newNode.left = this.left;
        //3.新的结点的右子树设置为当前节点的右子树
        newNode.right = this.right.left;
        //4.把当前节点的值改成当前结点的右子结点
        this.value = this.right.value;
        //5.把当前节点的右子节点设置为右子树的右子节点
        this.right = this.right.right;
        //6.把当前节点的左子节点设置为新的结点
        this.left = newNode;
    }

    // 右旋转
    private void rightRotate() {
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
