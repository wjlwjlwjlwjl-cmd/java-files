import java.rmi.server.ExportException;
import java.util.ArrayList;

public class MyBlockingQueue{
    public static void main(String[] args){
        BlockingQueue bq = new BlockingQueue(5);
        Thread t1 = new Thread(()->{
            for(int i = 0; i < 10; i++){
                bq.add(i + "");
            }
        });
        Thread t3 = new Thread(()->{
            for(int i = 10; i < 20; i++){
                bq.add(i + "");
            }
        });
        Thread t2 = new Thread(()->{
            while(true)
                System.out.println(bq.take());
        });
        t1.start();
        t2.start();
        t3.start();
    }
}

class BlockingQueue{
    private int _head;
    private int _tail;
    private String[] _con;
    private int _size;
    private Object locker;

    public BlockingQueue(int size){
        _con = new String[size + 1];
        _head = 0;
        _tail = 0;
        _size = size + 1;
        locker = new Object();
    }

    public void add(String s){
        synchronized(locker){
            while(isFull()){
                try{
                    System.out.println("full, waiting...");
                        locker.wait();
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
            _con[_tail] = s;
            _tail = (_tail + 1) % _size;
            locker.notify();
        }
    }

    public String take(){
        synchronized(locker){
            if(isEmpty()){
                try{
                    System.out.println("empty, waiting...");
                        locker.wait();
                }
                catch(Exception e){
                   System.out.println(e); 
                }
            }
            String ret = _con[_head];
            _head = (_head + 1) % _size;
            locker.notify();
            return ret;
            
        }
    }

    public boolean isEmpty(){
        return _head == _tail;
    }

    public boolean isFull(){
        return (_tail + 1) % _size == _head;
    }
}
