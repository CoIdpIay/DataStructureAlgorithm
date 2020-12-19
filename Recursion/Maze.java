package Recursion;

/**
 * @author Master
 * @create 2020-12-18-14:30
 * @description 递归解决迷宫问题
 */
public class Maze {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        // 其中四周以及(3,1)和(3,2)是墙,目标找到从(2,1)到(7,5)的最短路径
        // 墙用1表示,未走的点用0表示,能够走通的点用2表示,不能够走通的点用3表示
        for (int col = 0; col < 7; col++) {
            map[0][col] = 1;
            map[7][col] = 1;
        }
        for (int raw = 0; raw < 8; raw++) {
            map[raw][0] = 1;
            map[raw][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;
        System.out.println("原地图:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        setWay(map, 1, 1);
        System.out.println("新地图:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean setWay(int[][] map, int raw, int col) {
        if (map[6][5] == 2) {  // 如果(6,5) 数值改为了2,说明已经走到了这个点
            System.out.println("已经找到终点");
            return true;
        } else {
            if (map[raw][col] == 0) {  // 如果当前的路没有走过
                map[raw][col] = 2; // 假定目前所在的点是可以走通的,在最终路线找到之前都是假定的
                // 采用的策略是下右上左
                if (setWay(map, raw + 1, col)) {
                    return true;
                } else if (setWay(map, raw, col + 1)) {
                    return true;
                } else if (setWay(map, raw - 1, col)) {
                    return true;
                } else if (setWay(map, raw, col - 1)) {
                    return true;
                } else {
                    // 说明此点不通
                    map[raw][col] = 3;  // 这边可以否定掉之前的假定
                    return false;
                }
            } else {
                // 1,2,3 都不需要再试探了,在主动试探的情况下,往回走是没有意义的
                return false;
            }
        }
    }
}

