package TreeDemo;

/**
 * @author Master
 * @create 2020-12-28-15:22
 * @description 以数组方式存储的二叉树, 实现分别通过前序,中序,后序顺序的遍历
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,3,6,8,10,14};
        System.out.println("数组形式的前序遍历结果为:");
        ArrBinaryTree abt = new ArrBinaryTree(arr);
        abt.preOrder();
        System.out.println();
        System.out.println("数组形式的前序遍历结果为:");
        abt.infixOrder();
        System.out.println();
        System.out.println("数组形式的前序遍历结果为:");
        abt.postOrder();
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(){
        preOrder(0);
    }

    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空无法遍历");
            return;
        }
        System.out.print( arr[index] + " ");
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void infixOrder(){
        infixOrder(0);
    }

    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空无法遍历");
            return;
        }
        if (2 * index + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }
        System.out.print(arr[index] + " ");
        if (2 * index + 2 < arr.length) {
            infixOrder(2 * index + 2);
        }
    }


    public void postOrder(){
        postOrder(0);
    }
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空无法遍历");
            return;
        }
        if (2 * index + 1 < arr.length) {
            postOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            postOrder(2 * index + 2);
        }
        System.out.print( arr[index] + " ");
    }
}
