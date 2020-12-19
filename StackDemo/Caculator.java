package StackDemo;


/**
 * @author Master
 * @create 2020-12-15-16:14
 * @description  栈实现中缀表达式的计算
 */
public class Caculator {
    public static void main(String[] args) {
        // 假设运算的表达式
        String expression = "123*1-132+300";
        // 创建两个栈,一个符号栈一个数栈
        ArrayStack2 numStack = new ArrayStack2(20);
        ArrayStack2 operStack = new ArrayStack2(20);
        // 定义需要的相关变量
        int index = 0;
        char ch ;
        int num1;
        int num2;
        int oper;
        int res;
        String keepNum = "";
        // 遍历表达字符串中的字符
        while (true) {
            // 如果扫描到最后则退出循环
            if (index >= expression.length()) {
                break;
            }
            ch = expression.substring(index, index + 1).charAt(0);
            // 如果是这个字符是一个符号,则分情况讨论,否则添加到数栈
            if (operStack.isOper(ch)) {
                // 如果符号栈不是空
                if (!operStack.isEmpty()) {
                    // 判断符号的优先级,如果优先级小于等于栈顶的符号,则进行运算
                    if (operStack.priority(ch) <= operStack.priority(operStack.peak())) {
                        num2 = numStack.pop();
                        num1 = numStack.pop();
                        oper = operStack.pop();
                        numStack.add(operStack.cal(num1, num2, oper));
                    }
                    //优先级小于等于栈顶的符号,则进行运算后将符号入栈.优先级大于栈顶的符号,直接添加到符号栈
                }
                operStack.add(ch);
                index++;
            }
            // 字符是数字,要注意多位数的情况,如果后面的仍然是数字则继续扫描
            else {
                keepNum += ch;
                while (true) {
                    if (index == expression.length() - 1) {
                        index++;
                        break;
                    }
                    // 如果后一位是数字,则拼接
                    if (!numStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        keepNum += expression.substring(index + 1, index + 2);
                        index++;
                    } else {
                        // 是字符直接跳出小循环进入大循环
                        index++;
                        break;
                    }
                }
                numStack.add(Integer.parseInt(keepNum));
                keepNum = "";
            }
        }
        // 当字符串全部拆解完成
        while (true) {
            // 如果符号栈不为空,则计算到最后的结果,数栈中只有一个数字
            if (!operStack.isEmpty()) {
                num2 = numStack.pop();
                num1 = numStack.pop();
                oper = operStack.pop();
                numStack.add(operStack.cal(num1, num2, oper));
            } else {
                break;
            }
        }
        res = numStack.pop();
        System.out.println("最后的结果是" + res);
    }
}

// 用数组实现一个栈并增添新的方法
class ArrayStack2 {
    private int capacity;  // 栈的最大容量
    private int top = -1;  // 栈的顶部
    private int[] stack;  // 数组模拟栈

    public ArrayStack2(int capacity) {
        this.capacity = capacity;
        stack = new int[capacity];
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        stack = new int[capacity];
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        return this.top == -1;
    }

    // 判断是否为满
    public boolean isFull() {
        return top == capacity - 1;
    }

    // 添加到栈
    public void add(int num) {
        if (isFull()) {
            System.out.println("栈已经满了无法继续添加");
            return;
        }
        stack[++top] = num;
//            System.out.println("添加成功");
    }

    // 移除出栈
    public int pop() {
        if (isEmpty()) {
            System.out.println("栈已经空了无法继续删除");
            return -1;
        }
        return stack[top--];
    }

    // 查看栈顶的值
    public int peak() {
        return stack[top];
    }

    // 遍历整个栈
    // 遍历时从栈顶开始遍历
    public void list() {
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    // 返回运算符的优先级,优先级是程序员来确定的,优先级使用数字表示
    // 数字越大,则优先级就越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        }
        return -1;
    }

    // 判断是不是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算的方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
        }
        return res;
    }
}

