import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
把任务分成若干份，可以使用countDownLatch
*/

public class TestCountDownLatch{
    private static CountDownLatch latch = new CountDownLatch(100);
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();        
        for(int i = 0; i < 100; i++){
            int id = i;
            threadPool.submit(()->{
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    System.out.println(e);
                }
                System.out.println("Thread" + id + " done");
            });
            latch.countDown();
        }
        try{
            latch.await();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}

class TestSychronizedArrayList{
    public void test(){
        //对于写多读少的情况，使用Collections.synchronizedList更方便，自动为所有对应方法加了锁
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        //CopyOnWriteArrayList，采取写时拷贝的思想，拿到的数据一定是最新的完整版，不是中间版本
        //但是当数组非常大时，本身我们不想使用锁就是因为效率，但是拷贝空间时间效率都是很大的，就值得商榷了
        List<Integer> list2 = new CopyOnWriteArrayList<>();
    }
}

class TestCoCurrentHashMap{
    public void test(){
        ConcurrentHashMap<Integer, Integer> hash = new ConcurrentHashMap<>();        
        /*
        想要使用线程安全的哈希表，有两种选择：
        1. HashTable
        2. ConcurrentHashMap
        ConcurrentHashMap相比HashTable要轻量的多，原因是
        1. ConcurrentHashMap 没有采用全局锁，而是采用桶锁，即：只有同时修改一个桶中的元素的线程才需要竞争
        同一把锁。
        2. size 使用原子类，不需要使用锁
        3. 当需要扩容时，不会直接全部完成，而是采取化整为零的方式，每次put、get都会完成一部分，查找、写入的时候
        也会在两边都进行，这样压力就会小很多
        */
    }
}