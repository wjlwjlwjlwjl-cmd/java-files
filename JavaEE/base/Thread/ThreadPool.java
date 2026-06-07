import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool{
    public static void main(String[] args){
        /*
        public ThreadPoolExecutor(
        int corePoolSize,          // 1. 核心线程数
        int maximumPoolSize,       // 2. 最大线程数
        long keepAliveTime,        // 3. 空闲线程存活时间
        TimeUnit unit,             // 4. 时间单位
        BlockingQueue<Runnable> workQueue, // 5. 任务队列
        ThreadFactory threadFactory,      // 6. 线程工厂
        RejectedExecutionHandler handler  // 7. 拒绝策略

        拒绝策略主要有四种：
        1. AbortPolicy 线程池直接异常退出
        2. SubmitCallsPolicy 调用submit的线程转去执行任务
        3. DiscardPolicy 抛弃最新的任务
        4. DiscardOldestPolicy 抛弃最老的任务

        ExecutorService可以通过 newCachedThreadPool 或者 newFixedThreadPool 获得
        */
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for(int i = 0; i < 100; i++){
            threadPool.submit(()->{
                System.out.println(Thread.currentThread().getName());
            });
        }
    }
}