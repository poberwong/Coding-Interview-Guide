import java.util.Stack;

/**
 * 分析:
 * 这个解法和上一种是同工异曲的,在对数据栈进行操作的时候,都是保证了
 * stackMin里的数据呈现金字塔式的排列.与上一种方案不同的是,这里在push
 * 的时候,多了一层判断,在新数据大于stackMin的栈顶数据时,使用栈顶(当前)
 * 最小的数重复压栈(相当于占位).这样在弹栈的时候,就不需要使用判断对stackMin
 * 这个栈进行维护了,只需要将两个栈都做下pop即可.
 * Created by Bob on 15/11/3.
 */
public class C1_1_2 {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public C1_1_2() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int newNum) {
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(newNum);
        } else if (newNum <= this.getMin()) {
            this.stackMin.push(newNum);
        } else this.stackMin.push(this.getMin());

        this.stackData.push(newNum);
    }

    public int pop() {
        if (this.stackMin.isEmpty()) {
           throw new RuntimeException("your stack is empty");
        }
        this.stackMin.pop();
        return this.stackData.pop();
    }

    public int getMin() {
        if (this.stackMin.isEmpty()) {
           throw new RuntimeException("your stack is empty");
        }
        return this.stackMin.peek();
    }
}
