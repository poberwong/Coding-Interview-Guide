import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目: 猫狗队列
 * 给出宠物,狗,猫三个类,要求如下:
 * 实现一种猫狗队列的结构:
 * 1. 用户可以调用add 方法将cat类或者dog类的实例放入队列中
 * 2. 调用pollAll 方法,将队列中所有的实例按照进队列的先后次序依次弹出
 * 3. 调用pollDog 方法,将队列中dog类的实例按照进队列的先后次序弹出
 * 4. 调用pollCat 方法,将队列中cat类的实例按照仅队列的先后次序弹出
 * 5. 用户调用isEmpty 方法,检查队列中是否还有dog或者cat类的实例
 * 6. 用户调用isDogEmpty 方法,检查队列中是否还有dog类的实例
 * 7. 用户调用isCatEmpty 方法,检查队列中是否还有cat类的实例
 * <p>
 * Created by Bob on 15/12/13.
 */

class Pet {
    private String type, name;

    public Pet(String type) {
        this.type = type;
    }

    public Pet(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getPetName() {
        return name;
    }

    public String getPetType() {
        return type;
    }
}

class Dog extends Pet {
    public Dog() {
        super("dog");
    }

    public Dog(String name) {
        super("dog", name);
    }
}

class Cat extends Pet {
    public Cat() {
        super("cat");
    }

    public Cat(String name) {
        super("cat", name);
    }
}

/**
 * 因为不能动之前原有的三个类,我们只好通过
 * 创建一个增强型Pet类,它有一个计数器,表示加入队列的时间
 */
class PetEnhance {
    private Pet pet;
    private long count;

    public PetEnhance(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public long getCount() {
        return count;
    }

    public String getPetType() {
        return this.pet.getPetType();
    }
}

/**
 * 1. 这里有两个队列,来分别存放对应的猫,狗.
 * 在入队的时候根据类型识别,然后分别包装盖上时间戳入各自队
 * 2. 出队的时候,分别对两个队列队首元素进行时间判断,小的poll并返回
 * 3. 其他操作都非常简单
 */
class DogCatQueue {
    private Queue<PetEnhance> dogQ;
    private Queue<PetEnhance> catQ;
    private long count;

    public DogCatQueue() {
        this.dogQ = new LinkedList<>();
        this.catQ = new LinkedList<>();
        this.count = 0;
    }

    public void add(Pet pet) {
        if (pet.getPetType().equals("dog")) {
            this.dogQ.add(new PetEnhance(pet, count++));
        } else if (pet.getPetType().equals("cat")) {
            this.catQ.add(new PetEnhance(pet, count++));
        } else {
            throw new RuntimeException("error, not dog or cat");
        }
    }

    public Pet pollAll() {
        if (!this.catQ.isEmpty() && !this.dogQ.isEmpty()) {
            if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
                return this.dogQ.poll().getPet();
            } else {
                return this.catQ.poll().getPet();
            }
        } else if (!this.dogQ.isEmpty()) {
            return this.dogQ.poll().getPet();
        } else if (!this.catQ.isEmpty()) {
            return this.catQ.poll().getPet();
        } else {
            throw new RuntimeException("no pet in queue");
        }
    }

    public Dog pollDog() {
        if (!this.dogQ.isEmpty()) {
            return (Dog) this.catQ.poll().getPet();
        } else {
            throw new RuntimeException("no dog in queue");
        }
    }

    public Cat pollCat() {
        if (!this.catQ.isEmpty()) {
            return (Cat) this.catQ.poll().getPet();
        } else {
            throw new RuntimeException("no cat in queue");
        }
    }

    public boolean isEmpty() {
        return this.dogQ.isEmpty() && this.catQ.isEmpty();
    }

    public boolean isDogEmpty() {
        return this.dogQ.isEmpty();
    }

    public boolean isCatEmpty() {
        return this.catQ.isEmpty();
    }

}

/**
 * Subscribe:
 * 在这个模块中,我们也可以把狗,猫这种PetEnhance根据时间戳来
 * 存入一个队列中,只是这样设计的话,时间复杂度就会变得非常高.
 * 此时,出队就变的很麻烦,因为出队变得不在是实际意义上的出队:
 * 需要实现对任意位置的一个元素进行出队.不过这样的话,对于
 * pollAll方法来讲,变得非常简单,只需要queue.poll即可
 */
class DogCatQueueSpare {
    private Queue<Pet> petQueue;

    public DogCatQueueSpare() {
        this.petQueue = new LinkedList<>();
    }

    public void add(Pet pet) {
        if (pet.getPetType().equals("dog") || pet.getPetType().equals("cat")) {
            this.petQueue.add(pet);//这里没必要使用count了,因此我们使用0来代替之
        } else {
            throw new RuntimeException("error, not dog or cat");
        }
    }

    public Pet pollAll() {
        if (!this.petQueue.isEmpty()) {
            return this.petQueue.poll();
        } else {
            throw new RuntimeException("no pet in queue");
        }
    }

    public Dog pollDog() {

        if (!this.isDogEmpty()) {
            for (Pet pet : petQueue) {
                if (pet.getPetType().equals("dog")) {
                    petQueue.remove(pet);
                    return (Dog) pet;
                }
            }
            throw new RuntimeException("program is error");
        } else {
            throw new RuntimeException("no dog in queue");
        }
    }

    public Cat pollCat() {
        if (!this.isDogEmpty()) {
            for (Pet pet : petQueue) {
                if (pet.getPetType().equals("cat")) {
                    petQueue.remove(pet);
                    return (Cat) pet;
                }
            }
            throw new RuntimeException("program is error");
        } else {
            throw new RuntimeException("no cat in queue");
        }
    }

    public boolean isEmpty() {
        return this.petQueue.isEmpty();
    }

    public boolean isDogEmpty() {
        for (Pet pet : petQueue) {
            if (pet.getPetType().equals("dog"))
                return false;
        }
        return true;
    }

    public boolean isCatEmpty() {
        for (Pet pet : petQueue) {
            if (pet.getPetType().equals("cat"))
                return false;
        }
        return true;
    }

}


public class C1_4 {
    public static void main(String[] args) {
        DogCatQueueSpare queue= new DogCatQueueSpare();
        queue.add(new Dog("Tom"));
        queue.add(new Cat("July"));
        queue.add(new Cat("Terry"));
        queue.add(new Dog("Aky"));
        queue.add(new Cat("Sony"));

        System.out.println(queue.isCatEmpty());
        System.out.println(queue.isDogEmpty());

        System.out.println(queue.pollCat().getPetName());
        System.out.println(queue.pollCat().getPetName());
        System.out.println(queue.pollCat().getPetName());

        System.out.println(queue.pollDog().getPetName());
        System.out.println(queue.pollDog().getPetName());

    }

}

/**
 * Notice:
 *      此处为Pet, Dog, Cat加相关name的元素主要是为了调试方便,与程序算法无关
 */
