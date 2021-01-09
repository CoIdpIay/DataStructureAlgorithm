package TreeDemo.HuffmanTree;

import java.util.*;

/**
 * @author Master
 * @create 2021-01-07-14:18
 * @description Huffman压缩
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String contents = "i like like like java do you like a java";
        byte[] contentsBytes = contents.getBytes();
        byte[] zip = HuffmanZip(contentsBytes);
        System.out.println("Huffman编码后的bytes[]为" + Arrays.toString(zip));
    }

    // 构造一个存放HuffNode的数组
    private static List<HuffNode> getNodes(byte[] bytes) {
        ArrayList<HuffNode> nodes = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();  // 用于存放data极其所出现的次数,用于后续的构造Node
        for (byte b : bytes) {
            Integer counts = map.get(b);
            if (counts == null) {
                map.put(b, 1);
            } else {
                map.put(b, ++counts);
            }
        }
        // map的遍历方式
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new HuffNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 通过传入一个List构建huffman树
    private static HuffNode createHuffmanTree(List<HuffNode> Nodes) {
        while (Nodes.size() > 1) {
            // 首先对数组进行从小到大排序
            Collections.sort(Nodes);
            // 取出最小的两个
            HuffNode left = Nodes.get(0);
            HuffNode right = Nodes.get(1);
            HuffNode newNode = new HuffNode(null, left.weight + right.weight);
            newNode.left = left;
            newNode.right = right;
            Nodes.add(newNode);
            Nodes.remove(left);
            Nodes.remove(right);
        }
        // 最后一个就是huffman树的根节点
        return Nodes.get(0);
    }

    public static void preOrder(HuffNode root) {
        if (root == null) {
            System.out.println("树为空");
        }
        root.preOrder();
    }

    // 获得Huffman树对应的编码表
    // 思路:
    // 1.将赫夫曼编码表存放在一个Map<Byte,String>中-->{32:01,97:100,100:11000.......}
    // 2.在生成Huffman编码表示的时候需要去拼接路径,定义一个StringBuilder存储某个叶子节点的路径
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 功能:将传入的node节点的所有叶子节点的Huffman编码存放到huffmanCodes中
     *
     * @param node          传入节点
     * @param code          Huffman编码,左子节点是0,右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getHuffmanCodes(HuffNode node, String code, StringBuilder stringBuilder) {
        stringBuilder.append(code);
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder); // 将code加入到stringBuilder1中
        if (node != null) {
            // 判断当前节点是叶子节点还是非叶子节点
            if (node.data == null) {
                // 向左递归
                getHuffmanCodes(node.left, "0", stringBuilder1);
                // 向右递归
                getHuffmanCodes(node.right, "1", stringBuilder1);
            } else { // 节点是叶子节点,则将存储的data和到达这里的路径存储到map中
                huffmanCodes.put(node.data, stringBuilder1.toString());
            }
        }
    }

    /**
     * @param bytes        原始的字符串对应的byte数组
     * @param huffmanCodes 生成的字符与huffman编码对应的map
     * @return 返回huffman编码处理后的byte数组
     * 举例 StringContents = "i like like like java do you like a java" => byte[]
     * 返回的是将"i like like like java do you like a java"转换成Huffman编码字符串对应的byte[].
     * 每8位一个字节,放到这个数组中,每8位又可以构成唯一的数字,所以可以通过更少的数来表示Huffman编码字符串
     * 假设最前8位是10101000转化为具体某个字节的过程是:第一位不变后续位减1即10101000 - 1 => 10100111(反码)
     * 第一位不变其他位取反=> 11011000,最终HuffmanCodeByte[0] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            // 1.按照编码表将ascii值数组拼成一个字符串
            stringBuilder.append(huffmanCodes.get(b));
        }
        // 2.获得输出结果数组的长度,如果能够直接被8整除则长度为length() / 8否则要加1
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] huffmanCodeBytes = new byte[len];
        // 3.将字符串按每八位取转化成字节
        int index = 0;
        String subStr;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            if (i + 8 > stringBuilder.length()) {
                // 如果越界了直接取到最后
                subStr = stringBuilder.substring(i);
            } else {
                subStr = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(subStr, 2);
        }
        return huffmanCodeBytes;
    }

    /**
     * 功能 封装上面的功能
     * @param contentsBytes 每个字符对应的ascii码值组成的数组
     * @return Huffman编码后的数组
     */
    private static byte[] HuffmanZip(byte[] contentsBytes){
        List<HuffNode> nodes = getNodes(contentsBytes);  // 获得了存放HuffNode的数组,每个节点存放了字符极其出现的次数
        // 构建Huffman树
        HuffNode root = createHuffmanTree(nodes);
        // 生成Huffman编码
        getHuffmanCodes(root, "", stringBuilder);
        // 生成ffman编码后的bytes[]
        return zip(contentsBytes, huffmanCodes);
    }
}

class HuffNode implements Comparable<HuffNode> {
    Byte data; // 存放的是字符对应的ascii值
    int weight;  // 存放的是字符出现的次数
    HuffNode left;
    HuffNode right;

    public HuffNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public int compareTo(HuffNode o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "HuffNode[" +
                "data=" + data +
                ", weight=" + weight +
                ']';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}