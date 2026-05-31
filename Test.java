import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Test{
    public static void main(){

    }
}

class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        HashMap<Integer, Integer> map = new HashMap<>(); //种类、次数
        int left = 0, right = 0;
        int len = 0;
        while(right < n){
            if(map.containsKey(fruits[right])){
                map.put(fruits[right], map.get(fruits[right]) + 1);
            }
            else{
                map.put(fruits[right], 1);
            }
            while(map.size() > 2){
                map.put(fruits[left], map.get(fruits[left] - 1));
                if(map.get(fruits[left]) == 0){
                    map.remove(fruits[left]);
                }
                left++;
            }
            len = Integer.max(len, right - left + 1);
        }
        return len;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int n1 = s.length();
        int n2 = p.length();
        if(n1 < n2){
            return new ArrayList<Integer>();
        }
        List<Integer> ret = new ArrayList<>();

        HashMap<Character, Integer> hash = new HashMap<>();
        for(int i = 0; i < n2; i++){
            hash.put(p.charAt(i), hash.getOrDefault(p.charAt(i), 0) + 1);
        }
        HashMap<Character, Integer> count = new HashMap<>();

        int left = 0, right = 0;
        while(right < 0){
            count.put(s.charAt(right), count.getOrDefault(s.charAt(right), 0) + 1);

            while(right - left + 1 > n2){
                if(count.containsKey(s.charAt(left))){
                    count.put(s.charAt(left), count.get(s.charAt(left)) - 1);
                }
                if(count.get(s.charAt(left)) == 0){
                    count.remove(s.charAt(left));
                }
                left++;
            }

            if(right - left + 1 == n2){
                if(count == hash){
                    ret.add(left);
                }
            }
            right++;
        }
        return ret;
    }
}