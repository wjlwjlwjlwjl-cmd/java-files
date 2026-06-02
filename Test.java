import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Test{
    public static void main(){
        String s = "hello";
        System.out.println(s.substring(1, 3));
    }
}

class Solution {
    public String minWindow(String s, String t) {
        String ret = new String();
        int n = s.length();
        HashMap<Character, Integer> hash = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            hash.put(t.charAt(i), hash.getOrDefault(hash, 0) + 1);
        }

        HashMap<Character, Integer> buf = new HashMap<>();
        int left = 0, right = 0;
        while(right < n){
            char chR = s.charAt(right);
            if(hash.containsKey(chR)){
                buf.put(chR, hash.get(chR) + 1);
            }
            while(buf.get(chR) > hash.get(chR)){
                char chL = s.charAt(left);
                if(hash.containsKey(chL)){
                    buf.put(chL, buf.get(chL) - 1);
                }
                left++;
            }
            if(hash.equals(buf)){
                if(ret.length() >= right - left + 1){
                    ret = s.substring(left, right + 1);
                }
            }
            right++;
        }
        return ret;        
    }
}