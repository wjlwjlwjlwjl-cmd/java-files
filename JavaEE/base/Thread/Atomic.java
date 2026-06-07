import java.util.concurrent.atomic.AtomicInteger;

public class Atomic{
    private static AtomicInteger count = new AtomicInteger();
    public static void main(String[] args){
        Thread t1 = new Thread(()->{
            for(int i = 0; i < 5000; i++){
                count.incrementAndGet();
            }
        });
        Thread t2 = new Thread(()->{
            for(int i = 0; i < 5000; i++){
                count.incrementAndGet();
            }
        });
        t1.start();
        t2.start();
        try{
            Thread.sleep(2000);
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println(count);
    }
}