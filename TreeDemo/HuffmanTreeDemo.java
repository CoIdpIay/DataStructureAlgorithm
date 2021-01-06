package TreeDemo;

import java.util.*;

/**
 * @author Master
 * @create 2021-01-06-11:01
 * @description
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{13,7,8,3,29,6,1};
        HuffmanTree huffmanTree = new HuffmanTree();
        Node root = huffmanTree.createHuffmanTree(arr);
        huffmanTree.preOrder(root);
    }
}

class HuffmanTree {
    ArrayList<Node> arrNode = new ArrayList<>();

    public void preOrder(Node root) {
        if (root == null) {
            System.out.println("树为空");
            return;
        }
        root.preOrder();
    }

    public Node createHuffmanTree(int[] arr) {
        for (int num : arr) {
            arrNode.add(new Node(num));
        }
        while (arrNode.size() > 1) {
            // 将数组里的元素从小到大排列
            Collections.sort(arrNode);
            // 取出最小的两个点
            Node left = arrNode.get(0);
            Node right = arrNode.get(1);
            // 将两者的和作为根节点构成一个树
            Node newNode = new Node(left.getValue() + right.getValue());
            newNode.setLeft(left);
            newNode.setRight(right);
            arrNode.add(newNode);
            // 将两者从数组中抹去
            arrNode.remove(left);
            arrNode.remove(right);
        }
        // 剩下的最后一个就是huffman树的根节点
        return arrNode.get(0);
    }
}

class Node implements Comparable<Node> {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return (this.value - o.value);
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this.toString());
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
