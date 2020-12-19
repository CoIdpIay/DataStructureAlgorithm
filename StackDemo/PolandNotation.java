package StackDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Master
 * @create 2020-12-17-11:09
 * @description 实现逆波兰表达式的计算器
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 先定义一个一个逆波兰表达式
        // 中缀表达式(3+4)*5-6 => "3 4 + 5 * 6 -"
        String expression = "(3+4)*5-155";
        List<String> ls = transfer(expression);
        System.out.println(ls);
        int res = PolandNotation.calculator(ls);
        System.out.println(res);
    }

    // 完成对逆波兰表达式的计算
    public static int calculator(List<String> list) {
        int num1;
        int num2;
        String res;
        // 创建一个栈,仅需一个即可
        Stack<String> stack1 = new Stack<>();
        for (String ele : list) {
            if (ele.matches("\\d+")) {
                stack1.push(ele);
            } else {
                num1 = Integer.parseInt(stack1.pop());
                num2 = Integer.parseInt(stack1.pop());
                if (ele.equals("+")) {
                    res = String.valueOf(num1 + num2);
                } else if (ele.equals("-")) {
                    res = String.valueOf(num2 - num1);
                } else if (ele.equals("*")) {
                    res = String.valueOf(num1 * num2);
                } else if (ele.equals("/")) {
                    res = String.valueOf(num2 / num1);
                } else {
                    throw new RuntimeException("运算符出错");
                }
                stack1.push(res);
            }
        }
        return Integer.parseInt(stack1.pop());
    }

    public static List<String> transfer(String expression) {
        // 首先创建一个栈和一个数组,栈用来存放运算符,数组用来暂存中间结果
        Stack<String> stack = new Stack<>();
        ArrayList<String> ls = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        int index = 0;
        String keepNum = "";
        // 通过字符串扫描不是很方便,直接将中缀表达式放进数组中
        for (int i = 0; i < expression.length(); i++) {
            strings.add(expression.substring(i, i + 1));
        }
        System.out.println(strings);
        // 扫描中缀表达式数组
        while (true) {
            if (index >= strings.size()) {
                break;
            }
            char item = strings.get(index).charAt(0);
            // 如果扫描的是运算符
            if (item < 48 && item > 41) {
                while (stack.size() != 0 && stack.peek().charAt(0) != '(' && priority(item) <= priority(stack.peek().charAt(0))) {
                    ls.add(stack.pop());
                }
                stack.push("" + item);
                index++;
            } else if (item == '(' || item == ')') {
                // 如果扫描的是括号
                while (true) {
                    if (item == 40) {
                        // 如果是左括号,直接压入stack
                        stack.add("" + item);
                        break;
                    } else {
                        // 如果是右括号,依次弹出stack并放入ls,直到遇到'('
                        if (stack.peek().charAt(0) == 40) {
                            stack.pop();
                            break;
                        } else {
                            ls.add(stack.pop());
                        }
                    }
                }
                index++;
            } else {
                // 扫描出的是数字.考虑多位数的情况
                keepNum += item;
                while (true) {
                    // 如果下一个仍然为数字则拼接
                    // 如果已经扫描结束,直接跳出循环
                    if (index >= strings.size()) {
                        break;
                    }
                    // 如果没到最后一个数字
                    else if (index < strings.size() - 1) {
                        if (strings.get(index + 1).charAt(0) > 47) {
                            keepNum += strings.get(index + 1);
                            index++;
                        } else {
                            ls.add(keepNum);
                            index++;
                            break;
                        }
                    } else {
                        // 如果到了最后一个数字,直接添加
                        ls.add(keepNum);
                        index++;
                    }
                }
                keepNum = "";
            }
        }
        while (stack.size() > 0) {
            ls.add(stack.pop());
        }
        return ls;
    }

    public static int priority(char ch) {
        if (ch == '*' || ch == '/') {
            return 1;
        } else if (ch == '+' || ch == '-') {
            return 0;
        } else {
            throw new RuntimeException("字符出错");
        }
    }
}


