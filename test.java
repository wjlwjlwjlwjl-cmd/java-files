import java.util.Collections;
import java.util.Arrays;
public class test {
    public static void main(String[] strs){
        int[] nums1 = {12,24,8,32};
        int[] nums2 = {13,25,32,11};
        for(int x: advantageCount(nums1, nums2)){
            System.out.print(x + " ");
        }
    }

    public static int[] advantageCount(int[] nums1, int[] nums2) {
        //8 12 24 32
        //11 13 25 32
        
        //tmp: 12 24 32 8
        //ret: 24 32 8 12
        int n = nums1.length;
        Integer[] index = new Integer[n]; 
        int[] ret = new int[n];
        for(int i = 0; i < n; i++){
            index[i] = i;
        }
        Arrays.sort(nums1);
        Arrays.sort(index, (Integer x, Integer y) -> nums2[x] - nums2[y]);
        int left = 0, right = n - 1;
        for(int i = 0; i < n; i++){
            if(nums1[i] > nums2[index[left]]){
                ret[index[left++]] = nums1[i];
            }
            else{
                ret[index[right--]] = nums1[i];
            }
        }
        return ret;
    }
}
