import java.util.concurrent.Semaphore;
public class TestSemaphore{
    private static int count;
    private static Semaphore semaphore = new Semaphore(1);
    public static void main(){
        Thread t1 = new Thread(()->{
            for(int i = 0; i < 5000; i++){
                try{
                    semaphore.acquire();
                    count++;
                    semaphore.release();
                }
                catch(InterruptedException e){
                    System.out.println(e);
                }
            }
        });

        Thread t2 = new Thread(()->{
            for(int i = 0; i < 5000; i++){
                try{
                    semaphore.acquire();
                    count++;
                    semaphore.release();
                }
                catch(InterruptedException e){
                    System.out.println(e);
                }
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