package Search;

import java.util.Arrays;

/**
 * @author Master
 * @create 2020-12-24-14:16
 * @description 插值查找,对于1-100的等差数组,找寻的速度要快于二分查找,尤其是靠近数组边界的数字
 */
public class InsertSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int index1 = insertSearch(arr, 34, 0, 99);
        System.out.println(index1);
    }

    public static int insertSearch(int arr[], int value, int left, int right) {
        System.out.println("正在调用线性插值查找");
        if (left <= right || value < arr[0] || value > arr[arr.length - 1]) {
            // 一种估测的手段,对于分布较为均匀的有序数组,可以通过这个方法更快的逼近找寻数值附近,但是前提是
            // 找寻的数字不能大于或小于数组的最大最小值,否则得到的mid或超出索引
            int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
            // 如果找寻的值大于mid对应的值则向右递归
            if (value > arr[mid]) {
                return insertSearch(arr, value, mid + 1, right);
            } else if (value < arr[mid]) {
                // 如果找寻的值小于mid对应的值则向左递归
                return insertSearch(arr, value, left, mid - 1);
            } else {
                return mid;
            }
        }
        return -1;
    }
}
