import java.util.PriorityQueue;

public class MyTimer{
    public static void main(String[] args){
        TimerTask t1 = new TimerTask(()->System.out.println("hello world"), 1000);
        TimerTask t2 = new TimerTask(()->System.out.println("hello world"), 2000);
        TimerTask t3 = new TimerTask(()->System.out.println("hello world"), 3000);
        TimerTask t4 = new TimerTask(()->System.out.println("hello world"), 4000);
        Timer timer = new Timer();
        timer.schedule(t1);
        timer.schedule(t2);
        timer.schedule(t3);
        timer.schedule(t4);
    }
}

class TimerTask implements Comparable<TimerTask>{
    private Runnable _task;
    private long _time;

    public int compareTo(TimerTask t){
        return Long.compare(this._time, t.getTime());
    }

    public TimerTask(Runnable task, long time){
        _task = task;
        _time = System.currentTimeMillis() + time;
    }

    void run(){
        _task.run();
    }

    long getTime(){
        return _time;
    }
}

class Timer{
    private PriorityQueue<TimerTask> pq;
    private Object locker = new Object();

    public void schedule(TimerTask task){
        synchronized(locker){
            pq.offer(task);
            locker.notify();
        }
    }
    
    public Timer(){
        pq = new PriorityQueue<>();
        Thread t = new Thread(()->{
            while(true){
                synchronized(locker){
                    try{
                        while(pq.isEmpty()){
                            locker.wait();
                        }
                        TimerTask task = pq.peek();
                        if(System.currentTimeMillis() < task.getTime()){
                            locker.wait(task.getTime() - System.currentTimeMillis());
                        }
                        else{
                            task.run();
                            pq.poll();
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
        });
        t.start();
    }
}