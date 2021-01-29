package GraphDemo;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Master
 * @create 2021-01-27-11:36
 * @description 图结构的邻接矩阵实现
 */
public class GraphDemo {
    public static void main(String[] args) {
        String[] verTexs = {"1","2","3","4","5","6","7","8"};
        Graph graph = new Graph(8);
        // 添加结点
        for (String verTex : verTexs) {
            graph.insertVertex(verTex);
        }
        // 添加边 A-B A-C B-C -D C-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        // 显示邻接矩阵
        graph.showGraph();
        System.out.println("深度优先搜索:");
        graph.dfs();
        System.out.println();
        System.out.println("广度优先搜索:");
        graph.bfs();
    }
}

class Graph {
    private ArrayList<String> vertexList;  // 存储顶点的集合
    private int[][] edges;  // 图对应的邻接矩阵
    private int numOfEdges;  // 表示边的数目
    private boolean[] isVisited; // 表示对应的顶点是否已经被访问过

    public Graph(int n) {
        vertexList = new ArrayList<String>(n);
        edges = new int[n][n];
        numOfEdges = 0;
    }

    /**
     * @return
     * @fuction 返回结点的个数
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * @return
     * @function 返回边的数目
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * function 返回索引对应的结点 0->"A" 1->"B"
     *
     * @param i 索引
     * @return
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * @param val1
     * @param val2
     * @return
     * @function 按索引返回结点间的权值
     */
    public int getWeight(int val1, int val2) {
        return edges[val1][val2];
    }

    /**
     * @param vertex 结点
     * @function 添加结点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * function 添加边
     *
     * @param val1   表示边两端的结点的其中一个的下标 "A" - "B" "A"->0 "B"->1
     * @param val2   表示边两端的结点的其中一个的下标
     * @param weight 权值
     */
    public void insertEdge(int val1, int val2, int weight) {
        edges[val1][val2] = weight;
        edges[val2][val1] = weight;
        numOfEdges++;
    }

    /**
     * @function 显示邻接矩阵
     */
    public void showGraph() {
        for (int i = 0; i < vertexList.size(); i++) {
            System.out.print("\t" + vertexList.get(i));
        }
        System.out.println();
        for (int i = 0; i < edges.length; i++) {
            System.out.print(vertexList.get(i) + "\t");
            for (int val : edges[i]) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    /**
     * @param index 当前结点的下标
     * @return 第一个邻接点的的下标
     * @function 得到当前结点的第一个邻接结点的下标
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param indexInitial 初始的结点的索引
     * @param indexPre     初始索引的第一个邻接点的索引
     * @return 下一个邻接点的索引
     * @function 根据前一个邻接节点的下标获得下一个邻接节点的下标
     * (verTex[indexInitial]在indexPre索引位置后的下一个邻接节点的索引)
     */
    public int getNextNeighbor(int indexInitial, int indexPre) {
        for (int i = indexPre + 1; i < vertexList.size(); i++) {
            if (edges[indexInitial][i] > 0) {
                return i;
            }
        }
        return -1;
    }
    // 深度优先遍历

    /**
     * @param isVisited 记录结点是否被访问过的状态表
     * @param i         当前遍历到的结点的索引,最开始i=0
     * @function 深度优先遍历
     */
    public void dfs(boolean[] isVisited, int i) {
        // 首先访问该结点,递归中访问该节点的前提是这个节点没有被访问过,然后再输出
        System.out.print(getValueByIndex(i) + "-->");
        // 将结点设置为已经访问过
        isVisited[i] = true;
        // 查找结点i的第一个邻接点w
        int w = getFirstNeighbor(i);
        while (w != -1) { // 如果找到了想要的邻接点
            if (!isVisited[w]) { // 改邻接点没有被访问过
                dfs(isVisited, w);
            }
            // 如果该邻接点已经被访问过了,找下一个邻接点
            w = getNextNeighbor(i, w);
        }
    }

    public void dfs() {
        isVisited = new boolean[8];
        // 遍历所有的结点,进行dfs回溯
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }
    // 广度优先遍历

    /**
     * @param isVisited
     * @functioni
     */
    public void bfs(boolean[] isVisited,int i) {
        int u;  // 表示队列的头节点对应的下标
        int w;  // 邻接节点w
        LinkedList queue = new LinkedList();  // 队列,记录访问信息
        // 输出节点信息
        System.out.print(getValueByIndex(i) + "-->");
        // 标记为已访问
        isVisited[i] = true;
        // 将节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            // 取到队列的头节点下标w
            u = (Integer) queue.removeFirst();
            // 得到第一个邻接点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {  // 是否找到
                // 是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "-->");
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                // 已经访问过了,以和u相连的w的下一个邻接点w
                w = getNextNeighbor(u, w);
            }
        }
    }

    /**
     * @function 遍历所有的点, 都进行广度优先搜索
     */
    public void bfs() {
        isVisited = new boolean[8];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (isVisited[i] != true) {
                bfs(isVisited, i);
            }
        }
    }
}

