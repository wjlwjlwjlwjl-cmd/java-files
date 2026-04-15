import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;
public class Sort{
    public static void main(String[] args){
        ArrayList<Integer> arr = new ArrayList<>(10);
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            arr.add(random.nextInt(20));
        }

        //bubbleSort(arr);
        insertSort(arr);

        for(int i = 0; i < 10; i++){
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }

    public static void bubbleSort(ArrayList<Integer> arr){
        if(arr.size() == 1){
            return;
        }
        boolean flag = false;
        for(int i = 0; i < arr.size(); i++){
            for(int j = 1; j < arr.size() - i; j++){
                if(arr.get(j - 1) > arr.get(j)){
                    Collections.swap(arr, j - 1, j);
                    flag = true;
                }
            }
            if(flag == false){
                break;
            }
        }
    }

    public static void insertSort(ArrayList<Integer> arr){
        int n = arr.size();
        if(n == 1) return; //1 4 7 2
        for(int i = 1; i < n; i++){
            int end = i;
            int tmp = arr.get(end);
            while(end > 0){
                if(arr.get(end - 1) > tmp){
                    arr.set(end, arr.get(end - 1));
                    end--;
                }
                else{
                    break;
                }
            }
            arr.set(end, tmp);
        }
    }
}