import java.util.ArrayList;
import java.util.Iterator;
public class SeqList {
    public static void main(String[] args){
        testArrayList();
    }

    public static void testArrayList(){
        ArrayList<Integer> al1 = new ArrayList<>();
        ArrayList<Integer> al2 = new ArrayList<>(10);
        al2.add(1);
        al2.add(2);
        al2.add(3);
        ArrayList<Integer> al3 = new ArrayList<>(al1);

        //ArrayList的迭代器遍历
        Iterator<Integer> listLt = al2.listIterator();
        while(listLt.hasNext()){
            System.out.println(listLt.next() + " ");
        }
    }

    public static void testSL(){
        SL sl = new SL(4);
        for(int i = 0; i < 100; i++){
            sl.add(i);
        }
        sl.add(0, 9999);
        sl.add(5, 9999);
        sl.add(102, 9999);
        System.out.println(sl.contains(9999));
        System.out.println(sl.contains(-1));
        System.out.println(sl.indexOf(-1));
        System.out.println(sl.indexOf(56));
        sl.set(56, 0);
        System.out.println(sl.get(56));
        System.out.println(sl.size());
        sl.print();

        System.out.println("after remove");
        sl.remove(9999);
        sl.remove(9999);
        sl.remove(9999);
        sl.print();
        sl.clear();
        sl.print();
    }
}

class SL<T>{
    private T[] _datas;
    private int _capacity = 10;
    private int _index = 0;

    private void grow(){
        T[] tmp = (T[])new Object[_capacity * 2];
        for(int i = 0; i < _capacity; i++){
            tmp[i] = _datas[i];
        }
        _capacity *= 2;
        _datas = tmp;
    }

    SL(){
        _datas = (T[])new Object[_capacity];
    }

    SL(int capacity) {
        _datas = (T[]) new Object[capacity]; //Java不能直接new泛型数组，需要newObject数组再强转来实现
        _capacity = capacity;
    }

    public void add(T val){
        if(_index == _capacity){
            grow();
        }
        _datas[_index++] = val;
    }

    public void add(int pos, T val){
        if(pos >= _capacity || pos < 0){
            return;
        }
        if(_index == _capacity){
            grow();
        }
        int cur = _index;
        int next = _index + 1;
        while(cur >= pos){
            _datas[next] = _datas[cur];
            next--; cur--;
        }
        _datas[next] = val;
        _index++;
    }

    public Boolean contains(T val){
        for(int i = 0; i < _index; i++){
            if(_datas[i].equals(val)) { //此处注意不能使用==，否则比较的是引用对象是否为同一个
                return true;
            }
        }
        return false;
    }

    public int indexOf(T val){
        for(int i = 0; i < _index; i++){
            if(_datas[i] == val){
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }

    public T get(int pos){
        if(pos < 0 || pos >= _index){
            return null;
        }
        return _datas[pos];
    }
    public void set(int pos, T val){
        if(pos < 0 || pos >= _index){
            return;
        }
        _datas[pos] = val;
    }

    public int size(){
        return _index;
    }

    public void remove(T val){
        int pos = 0;
        for(; pos < _index; pos++){
            if(_datas[pos].equals(val)){
                break;
            }
        }
        int cur = pos, next = pos + 1;
        while(next < _index){
            _datas[cur] = _datas[next];
            cur++;
            next++;
        }
        _index--;
    }

    public void clear(){
        _datas = (T[])new Object[0];
        _capacity = 0;
        _index = 0;
    }

    public void print(){
        for(int i = 0; i < _index; i++){
            System.out.printf("%d ", _datas[i]);
        }
    }
}