import java.util.Stack;

/**
 * 题目:
 * 一个栈中的元素为整型,现在想将该栈从顶到底从大到小排序,只允许申请一个栈.
 * 除此之外,可以申请新的变量,但不能申请额外的数据结构.
 * Created by Bob on 15/12/13.
 */
public class C1_5 {
    public static void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();

            /**
             * cur小于等于help栈顶元素,直接压栈.否则就一直出help栈直到小于等于为止
             * 此时倒回stack的部分为逆序.
             * 或者这样理解,将cur插入到help栈中合适的位置
             */
            while (!help.isEmpty() && cur > help.peek()) {
                stack.push(help.pop());
            }
            help.push(cur);
        }

        while (!help.isEmpty()) {//逆转倒入stack中
            stack.push(help.pop());
        }
    }

    /**
     * 栈,队列的toString输出都是以数组的方式输出
     * 与其特性无关
     * @param args
     */
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(2);
        stack.push(5);
        stack.push(4);
        sortStackByStack(stack);
        System.out.println(stack.toString());

    }
}
