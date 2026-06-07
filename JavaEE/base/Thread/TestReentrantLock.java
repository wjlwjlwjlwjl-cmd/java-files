import java.util.concurrent.locks.ReentrantLock;

/*
ReentrantLock 和 synchronized 的区别
1. ReentrantLock 是 Java 的类，sychronized 是 JVM 通过 C++ 写的
2. ReentrantLock 加锁、解锁通过的是 lock、unlock 手动进行，sychronized是通过代码块进行的
3. ReentrantLock 提供了不阻塞的 tryLock 方法
4. ReentrantLock 的条件变量是通过 Condition 类实现的，可以通过 await/signal 实现一对多的功能，比 sychronized 的 wait/notify 要更强大
5. ReentrantLock 提供了公平锁，可以防止线程饥饿，但是吞吐量低
*/

public class TestReentrantLock{
    private static int count = 0;
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args){
        Thread t1 = new Thread(()->{
            for(int i = 0; i < 5000; i++){
                lock.lock();
                count++;
                lock.unlock();
            }
        });

        Thread t2 = new Thread(()->{
            for(int i = 0; i < 5000; i++){
                lock.lock();
                count++;
                lock.unlock();
            }
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }
        catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println(count);
    }
}