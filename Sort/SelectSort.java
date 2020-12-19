package Sort;

import java.util.Date;

/**
 * @author Master
 * @create 2020-12-19-15:43
 * @description  选择排序,时间复杂度O(n^2),80000个数据排序用时2s
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] testList = new int[80000];
        for (int i = 0; i < 80000; i++) {
            testList[i] = (int) (Math.random() * 80000);
        }
        Date date1 = new Date();
        long time1 = date1.getTime();
        selectSort(testList);
        Date date2 = new Date();
        long time2 = date2.getTime();
        System.out.printf("运算的时间为%d秒",(int)(int)((time2 -time1) /1000));
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    index = j;
                }
            }
            if (index != i) {
                arr[index] = arr[i];
                arr[i] = min;
            }
        }
//        for (int i : arr) {
//            System.out.print(i + " ");
//        }
    }
}
