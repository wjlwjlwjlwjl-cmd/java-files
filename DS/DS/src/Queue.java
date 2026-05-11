import java.util.LinkedList;

public class Queue {
    public static void main(String[] args){
        testMyQueue();
    }

    public static void testMyQueue(){
        MyQueue<Integer> mq = new MyQueue<>();
        for(int i = 0; i < 10; i++){
            mq.offer(i);
        }
        System.out.println(mq.size());
        while(!mq.isEmpty()){
            System.out.println(mq.poll());
        }
    }
}

class MyQueue<T>{
    private LinkedList<T> list;
    public MyQueue(){
        list = new LinkedList<>();
    }

    public void offer(T val){
        list.addFirst(val);
    }

    public T poll(){
        T ret = list.get(0);
        list.removeFirst();
        return ret;
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }
}
