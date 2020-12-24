package Search;

/**
 * @author Master
 * @create 2020-12-24-9:26
 * @description  线性查找算法
 */
public class SequentialSearch {
    public static void main(String[] args) {
        int[] arr = {1,13,2,4,32};
        int index = sequentialSearch(arr,13);
        System.out.println(index);
    }

    public static int sequentialSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
