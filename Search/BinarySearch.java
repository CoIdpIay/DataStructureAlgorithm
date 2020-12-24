package Search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Master
 * @create 2020-12-24-10:15
 * @description  二分法查找有序数组,支持查找重复的值
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 7, 7, 7, 8, 9};
        int index = binarySearch1(arr, 7, 0, arr.length - 1);
        System.out.println(index);
        List<Integer> resIndex = binarySearch2(arr, 7, 0, arr.length - 1);
        System.out.println(resIndex.toString());
    }

    public static int binarySearch1(int[] arr, int value, int left, int right) {
        // 当left小于等于right时才具备递归的条件
        if (left <= right) {
            int mid = (left + right) / 2;
            // 如果查找的值小于中间值,向左递归
            if (value < arr[mid]) {
                return binarySearch1(arr, value, left, mid - 1);
                // 如果查找的值大于中间值,向右递归
            } else if (value > arr[mid]) {
                return binarySearch1(arr, value, mid + 1, right);
            } else {
                // 如果查找的值等于中间值,则找到这个值返回索引
                return mid;
            }
        } else {
            // 没有找到
            return -1;
        }
    }

    public static ArrayList<Integer> binarySearch2(int[] arr, int value, int left, int right) {
        // 当left小于等于right时才具备递归的条件
        if (left <= right) {
            int mid = (left + right) / 2;
            // 如果查找的值小于中间值,向左递归
            if (value < arr[mid]) {
                return binarySearch2(arr, value, left, mid - 1);
                // 如果查找的值大于中间值,向右递归
            } else if (value > arr[mid]) {
                return binarySearch2(arr, value, mid + 1, right);
            } else {
                // 如果查找的值等于中间值,则找到这个值,以这个值为中心往两边扫描,找出所有可能的相同的值的索引
                ArrayList<Integer> resIndex = new ArrayList<>();
                resIndex.add(mid);
                int temp;
                // 向左扫描
                temp = mid - 1;
                while (true) {
                    // 当扫描出左边界,或者扫描到的不再是要找的值则直接结束扫描(因为有序,所以这种情况意味着扫描完毕)
                    if (temp < left || arr[temp] != value) {
                        break;
                    }
                    resIndex.add(temp);
                    temp--;
                }
                // 向右扫描
                temp = mid + 1;
                while (true) {
                    // 当扫描出右边界,或者扫描到的不再是要找的值则直接结束扫描(因为有序,所以这种情况意味着扫描完毕)
                    if (temp > right || arr[temp] != value) {
                        break;
                    }
                    resIndex.add(temp);
                    temp++;
                }
                return resIndex;
            }
        } else {
            // 没有找到,直接返回一个空的数组
            return new ArrayList<>();
        }
    }
}
