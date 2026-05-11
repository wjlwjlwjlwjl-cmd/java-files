import java.util.ArrayList;
import java.util.Collections;
public class Heap{
    public static void main(String[] args){
        testHeap();
    }

    public static void testHeap(){
        MyHeap heap = new MyHeap();
        heap.offer(0);
        heap.offer(4);
        heap.offer(8);
        heap.offer(2);
        heap.offer(7);
        heap.offer(1);
        heap.offer(5);
        heap.offer(9);
        heap.offer(3);
        heap.offer(6);
        heap.range();
        System.out.println();
        System.out.println(heap.peek());
        heap.heapSort();
        heap.range();
    }
}

class MyHeap{
    public ArrayList<Integer> arr;
    public MyHeap(){
        arr = new ArrayList<>();
    }

    public boolean offer(int val){
        if(arr.contains(val)){
            return false;
        }
        arr.addLast(val);
        addJustUp(arr.size() - 1);
        return true;
    }

    public void poll(){
        if(arr.isEmpty()){
            return;
        }
        Collections.swap(arr, 0, arr.size() - 1);
        arr.removeLast();
        addJustDown(0, arr.size());
    }

    public int peek(){
        return arr.get(0);
    }

    private void addJustDown(int parent, int end){
        int child = 2 * parent + 1;
        while(child < end){
            if(child + 1 < end && arr.get(child + 1) > arr.get(child)){
                child++;
            }
            if(arr.get(parent) < arr.get(child)){
                Collections.swap(arr, parent, child);
                parent = child;
                child = parent * 2 + 1;
            }
            else{
                break;
            }
        }
    }

    private void addJustUp(int child){
        int parent = (child - 1) / 2;
        while(parent >= 0){
            if(arr.get(parent) < arr.get(child)){
                Collections.swap(arr, parent, child);
                child = parent;
                parent = (child - 1) / 2;
            }
            else{
                break;
            }
        }
    }

    public void heapSort(){
        int end = arr.size() - 1;
        while(end > 0){
            Collections.swap(arr, end, 0);
            addJustDown(0, end);
            end--;
        } 
    }

    public void range(){
        for(int i = 0; i < arr.size(); i++){
            System.out.print(arr.get(i) + " ");
        }
    }
}