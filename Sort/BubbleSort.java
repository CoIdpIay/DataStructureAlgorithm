package Sort;

import java.util.Date;

/**
 * @author Master
 * @create 2020-12-19-10:25
 * @description 冒泡排序,时间复杂度O(n^2),80000个数据排序用时12s
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] testList = new int[80000];
        for (int i = 0; i < 80000; i++) {
            testList[i] = (int) (Math.random() * 80000);
        }
        Date date1 = new Date();
        long time1 = date1.getTime();
        bubbleSort(testList);
        Date date2 = new Date();
        long time2 = date2.getTime();
        System.out.printf("运算的时间为%d秒",(int)((time2 -time1) /1000));


    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int temp;
            boolean isSorted = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSorted = true;
                }
            }
            if (!isSorted) {
                break;
            }
        }
//        for (int i : arr) {
//            System.out.print(i + " ");
    }
}

