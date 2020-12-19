package Recursion;

/**
 * @author Master
 * @create 2020-12-18-16:53
 * @description
 */
public class EightQueen {
    int maxsize = 4;
    int[] arr = new int[maxsize];
    static int counter;

    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.check(0);
        System.out.println(counter);
    }

    // 编写一个方法放置第n个皇后
    public void check(int n) {
        if (n == maxsize) {
            list();
            return;
        }
        for (int i = 0; i < maxsize; i++) {
            arr[n] = i;
            if (judge(n)) {
                // 放置下一个
                check(n + 1);
            }
        }
    }

    // 检查放置的第n个皇后是否和之前已经摆放的皇后冲突
    //Math.abs(n - i) == Math.abs(arr[n] - arr[i]) 检测的是是否在同一斜线,等腰直角三角形
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 如果和之前的有冲突
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        // 如果不冲突
        return true;
    }

    // 显示结果
    public void list() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        counter++;
        System.out.println();
    }
}
