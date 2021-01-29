package TenAlgorithm;

/**
 * @author Master
 * @create 2021-01-29-10:57
 * @description  二分查找非递归实现
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int [ ] arr = {1,2,3,5,7,8};
        int index = BinarySearch(arr,8);
        System.out.println(index);
    }

    /**
     * @function 二分查找
     * @param arr 待查找的有序数组
     * @param target 待查找的数值
     * @return 目标数值在数组中的索引
     */
    public static int BinarySearch(int [] arr,int target){
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){
            int mid = (left+right) /2;
            if(arr[mid] == target){
                return  mid;
            }else if(arr[mid] > target){
                // 向左查找
                right = mid -1;
            }else{
                // 向右查找
                left = mid +1;
            }
        }
        return -1;
    }
}


