package Sort;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Master
 * @create 2020-12-23-11:20
 * @description 归并排序,算法复杂度O(nlogn),8000000万数据排序1212ms
 */
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {-1, 2, -2, 3, 0, 5, -6};
//        int[] temp = new int[arr.length];
//        mergeSort(arr, 0, arr.length - 1, temp);
//        System.out.println(Arrays.toString(arr));
        int[] testList = new int[8000000];
        int[] temp = new int[testList.length];
        for (int i = 0; i < 8000000; i++) {
            testList[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        long time1 = date1.getTime();
        mergeSort(testList, 0, testList.length - 1,temp);
        Date date2 = new Date();
        long time2 = date2.getTime();
        System.out.printf("运算的时间为%dms", (int) ((time2 - time1)));
    }

    /**
     * @param arr   原始数组
     * @param left  原始数组的最左端
     * @param right 原始数组的最右端
     * @param temp  临时数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        // 通过递归实现边拆解数组边合并的动作
        if (left < right) {
            int mid = (left + right) / 2;
            // 向左递归分解
            mergeSort(arr, left, mid, temp);
            // 向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * @param arr   需要排序的原始数组
     * @param left  左边有序序列的最前端索引
     * @param right 右边有序序列的最后端索引
     * @param temp  用于合并的临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;  // 初始化索引,表示前半部分的前端
        int j = mid + 1; // 初始化索引,表示后半部分的前端
        int t = 0;  // 表示临时数组的索引
        // 步骤一:先把左右两半部分的数据(已经有序)按照规则填充到temp数组中
        // 当两半部分的元素没有比较结束
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        // 步骤二:当两半部分的元素比较结束之后,将有剩余的直接填充到temp后面
        while (i <= mid) { // 说明是左半部分有元素剩余
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) { // 说明是右半部分有元素剩余
            temp[t] = arr[j];
            t++;
            j++;
        }
        // 步骤三:将合并完成的数组放到原始数组中
        t = 0;  // 初始化t,准备再次遍历temp数组
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }
}
