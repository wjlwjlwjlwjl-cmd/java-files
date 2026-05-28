package JavaEE.Thread;

public class Sychronized{
    public static void main(String[] args){
        new TestSynchronized().test1();
    }
}

class TestSynchronized{
    private static int count = 0;

    synchronized private void add(){ //用自身作为锁对象
            count += 1;
    }

    public void test1(){
        Thread t1 = new Thread(()->{
            for(int i = 0; i < 5000; i++){
                add();
            }
        });
        Thread t2 = new Thread(()->{
            for(int i = 0; i < 5000; i++){
                add();
            }
        });

        t1.start();
        t2.start();
        try{
            Thread.sleep(1000);
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println(count);
    }
}