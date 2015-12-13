package interview;

/**
 * Created by Bob on 15/12/12.
 * Question from America for Java
 */

import java.io.IOException;
import java.io.InputStream;

//A1;
public class MrZhang {

    public void fillByteArray(InputStream s, byte[] b, int offset, Integer numReads, int readAmount) throws IOException {
        while (numReads > 0 ) {
            if (readAmount > b.length - offset){
                s.read(b, offset, b.length - offset);
                break;
            }
            s.read(b, offset, readAmount);
            offset += readAmount;
            numReads--;
        }
    }

}

//A2
/**
 * 一个简单的死锁类
 * 当DeadLock类的对象flag==1时（td1），先锁定o1,睡眠500毫秒
 * 而td1在睡眠的时候另一个flag==0的对象（td2）线程启动，先锁定o2,睡眠500毫秒
 * td1睡眠结束后需要锁定o2才能继续执行，而此时o2已被td2锁定；
 * td2睡眠结束后需要锁定o1才能继续执行，而此时o1已被td1锁定；
 * td1、td2相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁。
 */
class DeadLock implements Runnable{
    int flag = 1;
    static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        System.out.println("flag= "+ flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("0");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock td1 = new DeadLock();
        DeadLock td2 = new DeadLock();
        td1.flag = 1;
        td2.flag = 0;
        //td1,td2都处于可执行状态，但JVM线程调度先执行哪个线程是不确定的。
        //td2的run()可能在td1的run()之前运行
        new Thread(td1).start();
        new Thread(td2).start();

    }
}

//A3
/**
 * 1.配合字符串常量池,为了效率和安全
 *      a,如果字符串不可修改,那么常量池产生的意义就有了——字符串的复用,而且在使用的时候如果需要对字符串进行复制,只需要拷贝栈地址即可
 *      b,如果可修改,那么当多个变量同时引用同一块堆内存时,操作一个变量时相当于对所有变量操作,不安全
 *
 * 2.在多线程中,如果可变,需要使用线程同步来保护字符串不被多个线程改变
 *
 * 3.对于HashCode的值,在不可变的情况下,HashCode值只需要在字符串被创建的时候缓存之就好了,就不用一次又一次得重新计算.这也是HashMap
 *   用 String 做key的原因
 */


//A4
/**
 * 1.long表示普通的64位变量,当在多线程+32位的系统环境(JVM)下时,会造成字撕裂问题
 * 2.volatile 表示在多线程中,每一个线程对该关键字修饰的方法进行读写操作时,不会将计算结果
 *   保存在CPU的cache中而是直接存入内存,这也就保证了内存中的变量是最新的.因此它保证了所有线程对该变量的可见性.
 *   在一个线程写,多个线程读的情况下可以保证线程安全.多线程写则无法保证线程安全
 * 3.AtomicLong 表示原子类,使计算机对该类型变量的操作具有原子性,也就是说在多线程情况下,对该对象的操作不会被中断
 *   可以实现对该对象的线程安全
 */

//A5

//A6
/**
 * 这里将多数的字段都给到了255长度的VARCHAR,它是一种长度会根据具体数据来变化的
 * 数据类型.如果第二次存入的数据比第一次存入的要长,系统就会采取分页或拆分的方式来
 * 重新操作.在这种情况下虽然避免了因数据长度二导致的数值溢出,但是在update的时候造成了
 * 系统的高负荷.
 * 我们可以采取使用适当长度的CHAR来代替之,这样就避免了系统的高负荷压力,虽然会造成部分的资源浪费
 */

//A7


//A8
/**
 * -2147483648,和MIN_VALUE值是相等的,因为int类型的值范围是-2^31~2^31-1,而给它乘以-1后为2147483648在int中溢出,而它的二进制值依旧没有变
 * 第一位为符号位,因此它的int表示值依旧是-2147483648
 */

//A9
/**
 * 1.可以摆脱Java单继承的约束
 * 2.为其子类强制设定规范,即为子类提出需求,约定子类具备哪些功能.将具体功能交由子类实现
 * 3.完全解耦,使得想要具备某种功能的类直接去实现对应的接口即可
 */

//A10
/**
 * 1.在普通对象的使用中,只需要覆写equals即可. equals默认的相等方案是==,也就是说必须要地址相同(是同一个对象)才算相等.
 * 而我们普通情况下只需要覆写equals,让它按照我们所规定的对比方案来判定两个对象是否相等.而此时如果只覆写equals就可以了.
 * 如果是只覆写hashCode,除非是同一个对象,否则hashCode肯定不会相等.(如果你把hasCode覆写得跟equals一样,脱离本身的意义那就另说了)
 *
 * 在含有散列表的集合(如HashSet, HashMap, HashTable)中,就需要既覆写equals又要覆写hashCode,因为只有当两个方法都满足true时,才会判断两个对象相等
 *
 */