package JavaSE.Other;

/**
 * @author: krest
 * @date: 2021/6/2 12:26
 * @description: 栈的简单使用
 */
public class StackTest {
    public static void main(String[] args) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        stack.push('A');
        stack.push('B');
        System.out.println(stack.peek());
        System.out.println(stack.size());
    }
}
