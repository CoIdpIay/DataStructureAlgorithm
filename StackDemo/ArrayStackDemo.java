package StackDemo;

import java.util.List;

/**
 * @author Master
 * @create 2020-12-10-23:18
 * @description
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(10);
        for (int i = 0; i < 7; i++) {
            arrayStack.add(i);
        }
        for (int i = 0; i < 2; i++) {
            arrayStack.pop();
        }
        arrayStack.list();
    }
}

class ArrayStack {
    private int capcity;  // 栈的最大容量
    private int top = -1;  // 栈的顶部
    private int[] stack;  // 数组模拟栈

    public ArrayStack() {
    }

    public ArrayStack(int capcity) {
        this.capcity = capcity;
        stack = new int[capcity];
    }

    public int getCapcity() {
        return capcity;
    }

    public void setCapcity(int capcity) {
        this.capcity = capcity;
        stack = new int[capcity];
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        if (top == -1) {
            return true;
        }
        return false;
    }

    // 判断是否为满
    public boolean isFull() {
        if (top == capcity - 1) {
            return true;
        }
        return false;
    }

    // 添加到栈
    public void add(int num) {
        if (isFull()) {
            System.out.println("栈已经满了无法继续添加");
            return;
        }
        stack[++top] = num;
        System.out.println("添加成功");
    }

    // 移除出栈
    public int pop() {
        if (isEmpty()) {
            System.out.println("栈已经空了无法继续删除");
            return -1;
        }
        int temp = stack[top--];
        System.out.printf("删除的数是%d\n", temp);
        return temp;
    }

    // 遍历整个栈
    // 遍历时从栈顶开始遍历
    public void list() {
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}
