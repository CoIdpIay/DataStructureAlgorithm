package Sort;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Master
 * @create 2020-12-22-22:13
 * @description 快速排序, 时间复杂度O(nlogn), 8000000个数据排序1183ms
 * 快速排序是对冒泡排序的一种改进
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] testList = new int[8000000];
//        for (int i = 0; i < 8000000; i++) {
//            testList[i] = (int) (Math.random() * 8000000);
//        }
//        Date date1 = new Date();
//        long time1 = date1.getTime();
//        quickSort(testList, 0, testList.length - 1);
//        Date date2 = new Date();
//        long time2 = date2.getTime();
//        System.out.printf("运算的时间为%dms", (int) ((time2 - time1)));
        int[] arr = {1, 3, 2, 4, 6, 5, 8, 9, 7};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2]; // 中值
        int temp;
        int l = left;
        int r = right;
        while (l < r) {
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[r] > pivot) {
                r -= 1;
            }
            if (l == r) {  // 每次当两指针重合(只会在中值处重合)时意味着指针左侧全是小于中值的,指针右侧全是大于中值的
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 当某一个指针率先指向中值时,将另一个指针向自己方向移动
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }
        // 再进行左右侧递归前,将两个指针分开
        l++;
        r--;
        // 若左侧值大于1个,则进行左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 若右侧值大于1个,则进行右递归
        if (l < right) {
            quickSort(arr, l, right);
        }
    }
}
