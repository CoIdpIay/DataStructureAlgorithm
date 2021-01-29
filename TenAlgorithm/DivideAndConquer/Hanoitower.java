package TenAlgorithm.DivideAndConquer;

/**
 * @author Master
 * @create 2021-01-29-14:01
 * @description 汉诺塔问题
 */
public class Hanoitower {
    public static void main(String[] args) {
        char a = 'A';
        char b = 'B';
        char c = 'C';
        haniTower(2, a, b, c);
    }

    public static void haniTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从" + a + "移动到" + c);
        } else {
            // 1.当n>=2时,先把上面所有的盘从a移动到b
            haniTower(num - 1, a, c, b);
            // 2.将最下面一个盘从a移动到c
            System.out.println("第" + num + "个盘从" + a + "移动到" + c);
            // 3.将b上的所有盘子移动到c
            haniTower(num - 1, b, a, c);
        }
    }
}
