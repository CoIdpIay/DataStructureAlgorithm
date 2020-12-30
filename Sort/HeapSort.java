package Sort;

import java.util.Arrays;

/**
 * @author Master
 * @create 2020-12-30-21:19
 * @description 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        // 要求用大顶堆进行升序排列
        int[] arr = {4, 6, 8, 5, 9};
        sort(arr);

    }

    // 编写一个堆排序的方法
    public static void sort(int[] arr) {
        int temp = 0;

        // 将无序序列构建成一个堆,根据升序降序的需求选择大顶堆或小顶堆
        for (int i = arr.length /2 -1; i >=0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }

        // 将堆顶元素与末尾元素交换,将最大元素沉到数组末端
        // 重新调整结构使其满足堆定义,然后继续交换堆顶元素和当前末尾元素,反复执行调整+交换步骤直到整个序列有序
        for (int i = arr.length-1; i >0 ; i--) {
            // 交换
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,i);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 功能:完成将以i对应 的非叶子节点的树调整成大顶堆
     *
     * @param arr    待调整的数组
     *               举例 int arr[] = {4,6,8,5,9}; i =1 经过该方法变成{4,9,8,5,6}
     *               再次调用该方法 传入i= 0;得到{9,6,8,5,4}
     * @param i      非叶子节点在数组中的索引
     * @param length 表示多少个元素继续调整
     */
    // 将一个数组调整成大顶堆
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; // 先取出当前元素的值保存为临时变量
        // 开始调整
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++; // k指向右子节点
            }
            if(arr[k]> temp){ // 如果子节点大于父节点
                arr[i] = arr[k]; // 把较大的值赋给当前节点
                i=k;  // i指向k,继续循环比较
            }else{
                break;  // 从左至右从下至上
            }
        }
        // 当for循环结束后,将以i为父节点的树的最大值放在了最顶(局部)
        arr[i] = temp; //将temp放到调整后的位置
    }
}
