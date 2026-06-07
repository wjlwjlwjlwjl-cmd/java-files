import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BQ{
    public static void main(String[] args) {
        new TestBlockQueue().test();
    }
}

class TestBlockQueue{
    public void test(){
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(10);
        Thread t1 = new Thread(()->{
            try{
                System.out.printf("consumer get element: %s\n",bq.take());
            }
            catch(Exception e){
                System.out.println(e);
            }
        });
        Thread t2 = new Thread(()->{
            try{
                for(int i = 0; i < 26; i++){
                    bq.add('a' + i + "");
                    System.out.printf("productor put element: %s\n", 'a' + i + "");
                    Thread.sleep(1000);
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        });
        t1.start();
        t2.start();
    }
}
