public class LinkedList {
    public static void main(String[] args){
        LL ll = new LL();
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.remove(5);
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addLast(4);
        ll.remove(4);
        ll.removeAllKey(3);
        ll.addLast(100);
        System.out.println(ll.contains(3));
        System.out.println(ll.contains(9));
        ll.range();
    }
}

class LLNode<T>{
    T _data;
    LLNode next = null;
    LLNode(T val){
        this._data = val;
    }
}

class LL<T>{
    private LLNode _root = null;
    private LLNode _tail = null;
    private int size = 0;

    public void addFirst(T val){
        if(_root == null){
            _root = new LLNode(val);
            _tail = _root;
            size++;
            return;
        }
        size++;
        LLNode newNode = new LLNode(val);
        newNode.next = _root;
        _root = newNode;
    }

    public void addLast(T val){
        size++;
        LLNode newNode = new LLNode(val);
        _tail.next = newNode;
        _tail = newNode;
    }

    public boolean contains(T val){
        LLNode cur = _root;
        while(cur != null){
            if(cur._data == val){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public boolean remove(T val){
        size--;
        LLNode cur = _root;
        LLNode prev = null;
        while(cur != null){
            if(cur._data.equals(val)){
                break;
            }
            prev = cur;
            cur = cur.next;
        }
        if(cur == null){
            return false;
        }
        if(cur == _tail){
            _tail = prev;
        }
        if(cur == _root){
            _root = cur.next;
            return true;
        }
        prev.next = cur.next;
        return true;
    }

    public void removeAllKey(T val){
        while(remove(val));
    }

    public int size(){
        return size;
    }

    public void clear(){
        _root = null;
        _tail = null;
        size = 0;
    }

    public void range(){
        LLNode cur = _root;
        while(cur != null){
            System.out.print(cur._data + " ");
            cur = cur.next;
        }
    }
}
