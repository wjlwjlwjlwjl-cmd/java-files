public class DeadLock{
    public static void main(String[] args){
        new MemoryVisibility().test();
    }
}

class NestedLocks{
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();
    public void test(){
        Thread t1 = new Thread(()->{
            synchronized(lock2){
                System.out.println("获取锁1");
                synchronized(lock2){
                    System.out.println("获取锁2");
                }
            }
        });
        Thread t2 = new Thread(()->{
            synchronized(lock1){
                System.out.println("获取锁2");
                synchronized(lock2){
                    System.out.println("获取锁1");
                }
            }
        });
        t1.start();
        t2.start();
    }
}

class MemoryVisibility{
    private boolean flag = true;
    private int count = 0;
    public void test(){
        Thread t1 = new Thread(()->{
            while(flag){
            }
            System.out.println("Thread1 exit");
        });
        Thread t2 = new Thread(()->{
            while(count < 1000000){
                count++;
            }
            flag = false;
            System.out.println("flag is false");
            try{
                Thread.sleep(20);
            }
            catch(Exception e){
                System.out.println(e);
            }
        });
        t1.start();
        t2.start();
        while(t1.isAlive()){}
    }
}