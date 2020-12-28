package TreeDemo;

/**
 * @author Master
 * @create 2020-12-26-16:23
 * @description  二叉树
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "剑圣");
        HeroNode node2 = new HeroNode(2, "剑豪");
        HeroNode node3 = new HeroNode(3, "剑魔");
        HeroNode node4 = new HeroNode(4, "剑姬");
        // 先手动创建一个二叉树,此处采用非递归的方式创建
        BinaryTree bt = new BinaryTree();
        bt.setRoot(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        System.out.println("删除前的前序遍历:");
        bt.preOrder();
        bt.delNode(4);
        System.out.println("删除后的前序遍历:");
        bt.preOrder();
        System.out.println("查找到的结果是:");
        bt.preOrderSearch(3);
    }
}

class BinaryTree {
    private HeroNode root;
    HeroNode res;

    public void setRoot(HeroNode hn) {
        this.root = hn;
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("这是一颗空树");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("这是一颗空树");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("这是一颗空树");
        }
    }

    public HeroNode preOrderSearch(int id) {
        if (this.root == null) {
            return null;
        }
        res = this.root.preOrderSearch(id);
        System.out.println(res);
        return res;
    }

    public void delNode(int id) {
        // 如果删除的节点是非叶子节点,则删除该子树
        if (root == null && root.getId() == id) {
            root = null;
        } else {
            root.delNode(id);
        }
    }
}

class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;
    private HeroNode res;

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

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // 这里在节点中直接提供了遍历的方法,在树类中直接调用
    // 前序遍历的方法
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            // 向左递归
            this.left.preOrder();
        }
        if (this.right != null) {
            // 向右递归
            this.right.preOrder();
        }
    }

    // 中序遍历的方法
    public void infixOrder() {
        if (this.left != null) {
            // 向左遍历
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            // 向右遍历
            this.right.infixOrder();
        }
    }

    // 后序遍历的方法
    public void postOrder() {
        if (this.left != null) {
            // 向左遍历
            this.left.postOrder();
        }
        if (this.right != null) {
            // 向右遍历
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public HeroNode preOrderSearch(int id) {
        // 比较当前节点是不是索要找的,是则返回
        if (this.id == id) {
            return this;
        }
        // 判断当前节点的左子节点是否为空,如果不为空,则递归前序查找
        // 如果左递归前序查找,找到节点则返回
        if (this.left != null) {
            res = this.left.preOrderSearch(id);
        }
        if (res != null) {
            return res;
        }
        // 左递归前序查找,找到节点则返回,否则继续判断
        // 当前节点的右子节点是否为空,如果不空,则继续向右递归查找
        if (this.right != null) {
            res = this.right.preOrderSearch(id);
        }
        return res;
    }

    public void delNode(int id) {
        // 如果该点的左子节点不为空,并且左子节点就是要寻找的节点,则直接将该点的左子树删除
        if (this.left != null && this.left.id == id) {
            this.left = null;
            return;
        }
        // 如果该点的右子节点不为空,并且右子节点就是要寻找的节点,则直接将该点的右子树删除
        if (this.right != null && this.right.id == id) {
            this.right = null;
            return;
        }
        // 如果左边的子节点不为空,则将向左进行递归删除
        if (this.left != null) {
            this.left.delNode(id);
        }
        // 如果右边的子节点不为空,则将向右进行递归删除
        if (this.right != null) {
            this.right.delNode(id);
        }

        // this.left和this.right都是空的话则默认结束方法
    }
}

