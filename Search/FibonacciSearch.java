package Search;

import java.util.Arrays;

/**
 * @author Master
 * @create 2020-12-25-14:11
 * @description 斐波那契查找法又称黄金分割查找法
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000,1234};
        int[] fib = fibGenerator(maxSize);
        int index = fibonacciSearch(arr, 1234, fib);
        System.out.println(index);
    }

    // 生成一个斐波那契数列
    public static int[] fibGenerator(int maxSize) {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    /**
     * @param arr   待查找数组,注意数组一定是有序的否则斐波那契查找法失效
     * @param value 待查找的值
     */
    public static int fibonacciSearch(int[] arr, int value, int[] fib) {
        // 首先找到最接近数组长度且大于数组长度的斐波那契元素
        int k = 0;
        while (arr.length > fib[k]) {
            k++;
        }
        // 将原数组填充成fib[k] 长度的数组,这里接用Arrays类实现,填充的元素均是0,将其全变为数组最右端的值
        int[] temp = Arrays.copyOf(arr, fib[k]);  //  此处到底是fib[k]还是fib[k] - 1 待确定
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = arr[arr.length - 1];
        }
        int left = 0;
        int right = arr.length -1;
        // 循环终止条件:left > right
        while (left <= right) {
            int mid = left + fib[k - 1] - 1;
            if (temp[mid] < value) {
                // 如果这个查找的值大于mid对应的值,则向右递归
                left = mid + 1;
                //对于fib[k-1]的部分可以拆成fib[k-2] + fib[k-3],由k-1到k-2减了一个单位
                k--;
            } else if (temp[mid] > value) {
                // 如果这个查找的值小于mid对应的值,则向左递归
                right = mid - 1;
                //对于fib[k-2]的部分可以拆成fib[k-3] + fib[k-4],由k-1到k-3减了两个单位
                k -= 2;
            } else {
                // 考虑到mid可能定位到扩充的部分,在返回查找值的索引时增加以下判断
                return (mid <= right) ? mid : right;
            }
        }
        return -1;
    }
}

