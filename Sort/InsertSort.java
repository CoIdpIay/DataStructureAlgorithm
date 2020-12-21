package Sort;

import java.util.Date;

/**
 * @author Master
 * @create 2020-12-20-20:07
 * @description 插入排序, 时间复杂度O(n ^ 2), 80000个数据排序用时726ms
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] testList = new int[80000];
        for (int i = 0; i < 80000; i++) {
            testList[i] = (int) (Math.random() * 80000);
        }
        Date date1 = new Date();
        long time1 = date1.getTime();
        insertSort(testList);
        Date date2 = new Date();
        long time2 = date2.getTime();
        System.out.printf("运算的时间为%dms", (int) ((time2 - time1)));
    }

    public static void insertSort(int[] arr) {
        int insertValue;  // 用于记录插入的值
        int insertIndex;  // 用于记录插入的位置
        for (int i = 1; i < arr.length; i++) {
            insertValue = arr[i];
            insertIndex = i - 1;  // inserIndex与j是完全同步的,一旦循环停止则插入的索引为insertIndex+1
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > insertValue) {
                    arr[j + 1] = arr[j];  // 实现了数组元素的后移
                    insertIndex--;
                } else {
                    break;
                }
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }
//            System.out.println(Arrays.toString(arr));
        }
    }
}
