import java.util.LinkedList;
import java.util.Iterator;

public class UtilLinkedList{
    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        for(int i: list){
            System.out.println(i);
        }

        Iterator<Integer> it = list.listIterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        Iterator<Integer> rit = list.descendingIterator();
        while(rit.hasNext()){
            System.out.println(rit.next());
        }
    }
}