import java.util.Stack;

/**
 * TwoStacksQueue
 * 题目:
 *     编写一个类,用两个队列实现,支持队列的基本操作(add,poll,peek)
 * Created by Bob on 15/11/4.
 */
public class C1_2 {
    private Stack<Integer> stackAdd;
    private Stack<Integer> stackPoll;

    public C1_2() {
        this.stackAdd = new Stack<>();
        this.stackPoll = new Stack<>();
    }

    public void add(int newNum){//入队列
        stackAdd.push(newNum);
    }

    public int poll(){
        if (this.stackPoll.isEmpty() && this.stackAdd.isEmpty()){
            throw new RuntimeException("the queue is empty");
        } else if (this.stackPoll.isEmpty()){
            while(this.stackAdd.isEmpty()){
                this.stackPoll.push(this.stackAdd.pop();
            }
        }
        return this.stackPoll.pop();
    }

    public int peek(){
        if (this.stackPoll.isEmpty() && this.stackAdd.isEmpty()){
            throw new RuntimeException("the queue is empty");
        } else if (this.stackPoll.isEmpty()){
            while(this.stackAdd.isEmpty()){
                this.stackPoll.push(this.stackAdd.pop();
            }
        }
        return this.stackPoll.peek();
    }
}

/**
 * 分析:(此题目没什么难度,主要是注意事项)
 *  入队列就是向stackAdd push
 *  出队列和查找队列顶部元素的时候,正常直接处理stackPoll的栈顶元素即可
 *  但是因为我们在stackPoll为空的时候,需要把stackAdd里的所有数据全部压入之后(也就是逆序化)
 *  才可以进行操作.当然,也只有stackPoll为空的时候才需要且只能逆序化stackAdd里的元素.
 *  of course,如果两个栈都为空,只能说明队列里没有元素,需要注意
 */
