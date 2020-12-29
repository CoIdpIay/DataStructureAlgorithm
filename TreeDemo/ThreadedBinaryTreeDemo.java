package TreeDemo.ThreadedBinaryTree;

/**
 * @author Master
 * @create 2020-12-29-14:16
 * @description // 中序线索化二叉树,并实现线索化二叉树后的中序遍历
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "jack");
        HeroNode node2 = new HeroNode(3, "mary");
        HeroNode node3 = new HeroNode(6, "tom");
        HeroNode node4 = new HeroNode(8, "carl");
        HeroNode node5 = new HeroNode(10, "rose");
        HeroNode node6 = new HeroNode(14, "rose");
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        ThreadBinaryTree tbt = new ThreadBinaryTree();
        tbt.setRoot(node1);
        tbt.threadNodes(node1);
        System.out.println("测试线索化");
        System.out.println(node5.getLeft());
        System.out.println(node5.getRight());
        System.out.println("测试中序遍历:");
        tbt.infixOrder();
    }
}

class ThreadBinaryTree {
    private HeroNode root;
    HeroNode preNode = null;

    public void setRoot(HeroNode hn) {
        this.root = hn;
    }

    public void infixOrder() {
        HeroNode node = root;
        while (node != null) {  // 下面会将节点后推,如果推到了最后一个节点就循环终止
            while (node.getLeftType() == 0) {  // 满足条件则左节点依然连接的是左子树
                node = node.getLeft();
            }
            // 当第一次不满足条件时,说明该点是中序遍历的第一个点
            System.out.println(node);
            while (node.getRightType() == 1) { // 说明右边是连接的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    // 对二叉树进行线索化,以中序为例
    public void threadNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        // 先线索化左子树
        threadNodes(node.getLeft());
        // 线索化当前节点,left存放前驱节点
        if (node.getLeft() == null) {
            node.setLeft(preNode);
            node.setLeftType(1);
        }
        // 线索化前序节点(比较难理解这一块),right存放后驱节点
        if (preNode != null && preNode.getRight() == null) {
            preNode.setRight(node);
            preNode.setRightType(1);
        }
        // !!!每处理完一个节点后,让当前节点是下一个节点的前驱节点
        preNode = node;
        // 再线索化右子树
        threadNodes(node.getRight());
    }
}

class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;
    // 如果leftType等于0则表示左子节点为子数如果1则表示左子节点是前驱节点
    private int leftType;
    // 如果rightType等于0则表示右子节点为子数如果1则表示右子节点是后继节点
    private int rightType;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}