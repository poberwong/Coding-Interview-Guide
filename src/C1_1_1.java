import java.util.Stack;

/**
 * 设计一个有getMin功能的栈
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
