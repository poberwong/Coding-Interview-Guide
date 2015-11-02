import java.util.Stack;

/**
 * 设计一个有getMin功能的栈
 * 题目：
 *  实现一个特殊的栈，在实现栈基本功能的基础上，再实现返回栈中最小元素的操作
 * 要求：
 *  1、pop, push, getMin 操作的时间复杂度都是O(1)
 *  2、设计的栈类型可以使用现成的栈结构
 * Created by Bob on 15/11/2.
 */
public class C1_1_1 {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public C1_1_1(){
        this.stackData= new Stack<>();
        this.stackMin= new Stack<>();
    }

    public void push (int newNum){
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(newNum);
        } else if (newNum <= this.getMin()) {
            this.stackMin.push(newNum);
        }
        this.stackData.push(newNum);
    }

    public int pop(){
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("your stack is empty");
        }
        int value= this.stackData.pop();
        if (value== this.getMin()){
            this.stackMin.pop();
        }
        return value;
    }

    public int getMin(){
        if (this.stackMin.isEmpty()){
            throw new RuntimeException("your stack is empty");
        }
        return this.stackMin.peek();
    }
}
/**
 * 分析：
 *  使用了两个栈，一个是stackData，stackMin，前者是用来push和pop的主体栈，
 *  后者是用来获取当前栈中最小值的辅助栈。
 *  push：在给stackData push的时候，判断stackMin是否有值，没有的话直接给stackMin也push
 *  如果给stackData push的值不大于stackMin的栈顶值，那么同样给stackMin push（保证了stackMin里的数据是由栈底到栈定是递减的过程，
 *  也就是它栈顶元素一定是stackData里的最小值）
 *  否则不给stackMin做任何操作
 *
 *  pop：由我们的push方法可知，stackMin里的数据从栈定到栈底是递增的，因此同步的操作下，
 *  stackData对应的栈定元素必然大于等于stackMin的栈顶元素。
 *  因为我们出栈操作是：给stackData出栈后，如果这个元素等于stackMin的栈顶元素（也就是当前栈里的最小值），那么给stackMin出栈，
 *  因为栈里的最小值出栈了，所以这样可以保证了stackMin栈顶对stackData的有效性。
 *
 *  getMin：由上可知，stackMin的栈顶元素即就是数据栈里的最小值，直接peek即可
 */
