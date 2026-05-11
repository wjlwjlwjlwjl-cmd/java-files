import java.util.ArrayList;
public class Stack {
    public static void main(String[] args){
        testStack();
    }

    public static void testUtilStack(){

    }

    public static void testStack(){
        MyStack<Integer> ms = new MyStack<>();
        ms.push(1);
        ms.push(2);
        ms.push(3);
        ms.pop();
        System.out.println(ms.peek());
        System.out.println(ms.size());
        System.out.println(ms.isEmpty());
        ms.pop();
        ms.pop();
        System.out.println(ms.isEmpty());
        String str = "1";
        int a = Integer.valueOf(str);
    }
}

class MyStack<T>{
    private ArrayList<T> _arr;
    public MyStack(){
        _arr = new ArrayList<>(10);
    }

    public void push(T val){
        _arr.addLast(val);
    }
    
    public void pop(){
        _arr.removeLast();
    }

    public T peek(){
        if(_arr.isEmpty()){
            return null;
        }
        return _arr.get(_arr.size() - 1);
    }

    public int size(){
        return _arr.size();
    }

    public boolean isEmpty(){
        return _arr.isEmpty();
    }
}
