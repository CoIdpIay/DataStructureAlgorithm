package GraphDemo;

import java.util.ArrayList;

/**
 * @author Master
 * @create 2021-01-27-11:36
 * @description  图结构的邻接矩阵实现
 */
public class GraphDemo {
    public static void main(String[] args) {
        String []verTexs = {"A","B","C","D","E"};
        Graph graph = new Graph(5);
        // 添加结点
        for(String verTex:verTexs){
            graph.insertVertex(verTex);
        }
        // 添加边 A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        // 显示邻接矩阵
        graph.showGraph();
    }
}

class Graph{
    private ArrayList<String> vertexList;  // 存储顶点的结合
    private int[][] edges;  // 图对应的邻接矩阵
    private int numOfEdges;  // 表示边的数目

    public Graph(int n){
        vertexList  = new ArrayList<String >(n);
        edges = new int[n][n];
        numOfEdges = 0;
    }

    /**
     * @fuction 返回结点的个数
     * @return
     */
    public int getNumOfVertex(){
        return  vertexList.size();
    }

    /**
     * @function 返回边的数目
     * @return
     */
    public int getNumOfEdges(){
        return  numOfEdges;
    }

    /**
     * function 返回索引对应的结点 0->"A" 1->"B"
     * @param i 索引
     * @return
     */
    public String getValueByIndex(int i){
        return  vertexList.get(i);
    }

    /**
     * @function 按索引返回结点间的权值
     * @param val1
     * @param val2
     * @return
     */
    public int getWeight(int val1,int val2){
        return edges[val1][val2];
    }
    /**
     * @function 添加结点
     * @param vertex 结点
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);

    }

    /**
     * function 添加边
     * @param val1 表示边两端的结点的其中一个的下标 "A" - "B" "A"->0 "B"->1
     * @param val2 表示边两端的结点的其中一个的下标
     * @param weight 权值
     */
    public void insertEdge(int val1,int val2,int weight){
        edges[val1][val2] = weight;
        edges[val2][val1] = weight;
        numOfEdges++;
    }

    /**
     * @function 显示邻接矩阵
     */
    public void showGraph(){
        for (int i = 0; i < vertexList.size(); i++) {
            System.out.print("\t" + vertexList.get(i));
        }
        System.out.println();
        for (int i = 0; i < edges.length; i++) {
            System.out.print(vertexList.get(i) + "\t");
            for(int val : edges[i]){
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}

