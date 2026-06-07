import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/*
创建线程的方法
1. 重写 Runnable 的 run 方法
2. 重写 Callable 的 call 方法，并结合 FutureTask 使用
3. 使用 lambda 表达式
4. 继承 Thread 类
5. 使用线程池 ThreadPoolExecutor newFixedThreadPool newCachedThreadPool
*/

public class TestCallable{
    public static void main(){
        Callable<Integer> callable = new Callable<>(){
            @Override
            public Integer call(){
                int ret = 0;
                for(int i = 0; i< 500; i++){
                    ret += i;
                }
                return ret;
            }
        };
        FutureTask<Integer> future = new FutureTask<>(callable);
        Thread t = new Thread(future);
        t.start();
        try{
            System.out.println(future.get());
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
}