package JavaEE.Thread; 

//Thread，在 java.lang 包中，但是这个包现在是 java 默认导入的

public class MyThread{
    public static void main(String[] args){
        //重写 Thread 的 run 方法（线程的入口函数）
        //和线程的概念是耦合的
        //new Thread1(){}.run();

        //通过重写Runnable接口，并作为参数初始化Thread
        //使用的是 Runnalbe 的接口的概念，给线程池，也是这么用
        //new Thread(new Thread2()).start();

        //通过匿名内部类（一次性）
        //new TestThread(){}.test();

        //通过lambda表达式（Java托离不开类）
        //new TestThread2().test();

        /*
        * java中，lambda 表达式，本质上是一个函数式接口（只有一个方法的接口）
        * 为了解决lambda直接使用外部变量导致的变量生命周期问题，所以java使用形参的方式使用
        * 外部变量。但是这种方式容易让程序员误认为可以修改外部变量，所以直接不允许修改，使用的外部变量
        * 必须是 final 或者 effetctivly final
        * 
        * 但是这里使用外部静态变量可以，因为转换了语法，这里是内部类访问外部类静态成员变量（因为函数式接口是只有一个方法的接口，其实就是一个抽象类）
        */
        new Test().test2();
    }
}

class Test{
    //中断线程（这里的中断其实是终止的意思）
    public static boolean isFinished = false;
    public void test(){
        new Thread(()->{
            while(!isFinished){
                System.out.println("test");
                try{
                    Thread.sleep(1000);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        }).start();
        try{
            Thread.sleep(3000);
        }
        catch(Exception e){
            System.out.println(e);
        }
        isFinished = true;
    }

    public void test2(){
        Thread t = new Thread(()->{
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("test");
                try{
                    Thread.sleep(1000);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        });

        t.start();

        try{
            Thread.sleep(3000);
        }
        catch(Exception e){
            System.out.println(e);
        }
        t.interrupt();
    }
}

class Thread1 extends Thread{
    @Override
    public void run(){
        System.out.println("Thread by extending");
    }
}

class Thread2 implements Runnable{
    //重写runnalbe接口
    @Override
    public void run(){ //这里不能throws，因为重写的限制
        while(true){
            System.out.println("Thread by implement");
            try{
                Thread.sleep(1000); //Thread中有提供的休眠函数，单位是毫秒
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
}

class TestThread2{
    public void test(){
        Thread t = new Thread(()->{
            System.out.println("hello lambda thread");
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){
                System.out.println(e);
            }
        });
        System.out.println(t.isAlive());
        t.setDaemon(false); //设置为守护线程（由 JVM 接管，当 JVM 退出守护线程也会退出
        t.start();
        //System.out.printf("线程 ID: %d\n",t.getId());
        System.out.printf("线程名称: %s\n", t.getName());
        System.out.printf("线程状态: %s\n", t.getState());
        System.out.printf("线程优先级: %s\n", t.getPriority());
        System.out.println(t.isAlive());
        System.out.println(t.isDaemon());
    }
}

class TestThread{
    public void test(){
        //匿名内部类
        Thread t1 = new Thread() {
            @Override
            public void run() { //run，是线程运行的入口
                for(int i = 0; i < 10; i++){
                    System.out.println("subThread");
                    try{
                        Thread.sleep(1000);
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
        };
        t1.start(); //start 方法，是 JVM 创建线程
    }
}