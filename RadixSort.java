package Sort;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Master
 * @create 2020-12-23-19:18
 * @description 基数排序, 桶排序的增强版,时间复杂度O(n*k),k为桶的数量, 稳定性好, 8000000个数据排序时间654ms
 * 暂时没有实现负数版
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
//        radixSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] testList = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            testList[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        long time1 = date1.getTime();
        radixSort(testList);
        Date date2 = new Date();
        long time2 = date2.getTime();
        System.out.printf("运算的时间为%dms", (int) ((time2 - time1)));
    }

    public static void radixSort(int[] arr) {
        // 首先创建十个桶子,用于盛放数字0-9
        int[][] buckets = new int[10][arr.length];
        // 记录各个桶子里数字的个数
        int[] numOFNum = new int[10];
        // 确定总共要比较多少位次,通过找出最大值可以找到
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxSize = 1;
        // 确定位次
        while ((max / 10) != 0) {
            max /= 10;
            maxSize++;
        }
        // 将数组从桶里装进数据的时候用作索引
        int index = 0;
        for (int j = 0, n = 1; j < maxSize; j++, n *= 10) {
            for (int i = 0; i < arr.length; i++) {
                // 获取对应位数上的数字
                int num = arr[i] / n % 10;
                buckets[num][numOFNum[num]] = arr[i];
                numOFNum[num] += 1;
            }
            // 至此,所有的数已经按照位数放到对应的桶里面去了,将数从桶里面放出来
            for (int l = 0; l < numOFNum.length; l++) { // 遍历记录数组来获得桶子里数字的情况
                if (numOFNum[l] != 0) {  // 说明数字i对应的桶子里有数字
                    for (int m = 0; m < numOFNum[l]; m++) {
                        arr[index] = buckets[l][m];
                        index++;
                    }
                }
            }
            // 清空用于记录各个桶子数量的数组numOfNum
            for (int p = 0; p < numOFNum.length; p++) {
                numOFNum[p] = 0;
            }
            index = 0;
        }
    }
}
