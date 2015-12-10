import java.util.Stack;

/**
 * Reverse a Stack only with recursion
 * 题目:
 * 一个栈依次压入1,2,3,4,5, 那么从栈顶到栈底分别为5,4,3,2,1.
 * 将这个栈转置之后,从栈顶到栈底为为1,2,3,4,5,也就是实现栈中
 * 元素的逆序,只能用函数来实现,不能用其他数据结构.
 * Created by Bob on 15/12/9.
 */
public class C1_3 {
    /**
     * 移除并返回栈底元素
     * @param stack
     * @return
     */
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        int result= stack.pop();//result为栈顶元素,存放在每一层递归中的一个临时变量(递归过程中执行的逻辑)

        if (stack.isEmpty()){//递归终止条件,即回调条件
            return result;//开始回调
        } else {
            int last= getAndRemoveLastElement(stack);

            stack.push(result);//回调过程中执行的逻辑
            return last;//
        }
    }

    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }
}

