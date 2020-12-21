package Sort;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Master
 * @create 2020-12-21-11:52
 * @description shell排序, 时间复杂度O(n ^ 2), 800000个数据排序用时185ms
 *              作为简单插入排序的增强版,解决了简单排序的弊端即较小数在后面往前移位置效率低的问题
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] testList = new int[800000];
        for (int i = 0; i < 800000; i++) {
            testList[i] = (int) (Math.random() * 800000);
        }
        Date date1 = new Date();
        long time1 = date1.getTime();
        shellSort(testList);
        Date date2 = new Date();
        long time2 = date2.getTime();
        System.out.printf("运算的时间为%dms", (int) ((time2 - time1)));
//        int [] arr = {1,3,2,4,6,5,8,9,7};
//        shellSort(arr);
    }

    public static void shellSort(int arr[]) {
        int insertValue;
        int insertIndex;
        for (int step = arr.length / 2; step >= 1; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                insertIndex = i - step;
                insertValue = arr[i];
                for (int j = i - step; j >= 0; j -= step) {
                    if (arr[j] > insertValue) {
                        arr[j + step] = arr[j];
                        insertIndex -= step;
                    } else {
                        break;
                    }
                }
                arr[insertIndex + step] = insertValue;
            }
//            System.out.println(Arrays.toString(arr));
        }
    }
}

