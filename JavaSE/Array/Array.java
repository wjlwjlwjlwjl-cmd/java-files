package JavaSE.Array;

public class Array{
    public static void main(String[] args){
        int[] arr1 = new int[10]; //动态初始化
        String[] strs = new String[]{"wang", "jia", "le"}; //静态初始化
        double[] arr3 = {1.1, 2.2}; //省略格式
        double arr2[] = {1.1, 2.2}; //采取C语言的风格

        //动态、静态初始化可以分成两步
        int[] arr4;
        arr4 = new int[10];
        int[] arr5;
        arr5 = new int[]{1, 2, 3};
        //省略格式不可以分为两部
        //关于动态初始化用于构建数组的默认值，都是各种类型的0(boolean是false)
        for(int i = 0; i < strs.length; i++){
            System.out.println(strs[i]);
        }

        for(String s: strs){
            System.out.println(s);
        }
        int[] ret = test(5);
        for(int i: ret){
            System.out.println(i);
        }

        int[] array = {5, 9, 3, 0, 6, 1, 2, 8};
        bubble_sort(array);
        for(int x: array){
            System.out.printf("%d ", x);
        }
        int[][] array1 = new int[2][];
        array1[0] = new int[4];
        array1[0] = new int[5];
    }

    public static int[] test(int n){
        int[] arr = new int[n];
        arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;
        for(int x = 2; x < n; x++){
            arr[x] = arr[x - 1] + arr[x - 2];
        }
        return arr;
    }

    public static void bubble_sort(int[] arr){
        int n = arr.length;
        for(int i = 0; i < n; i++){
            for(int j = 1; j < n - i; j++){
                if(arr[j - 1] > arr[j]){
                    int tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static int min_insertations(String s){
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int j = 0; j < n; j++){
            for(int i = j; i >= 0; i--){
                if(s.charAt(i) == s.charAt(j)){
                    if(i == j || i + 1 == j){
                        dp[i][j] = 0;
                    }
                    else{
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                else{
                    if(i + 1 == j){
                        dp[i][j] = 1;
                    }
                    else{
                        dp[i][j] = Integer.min(dp[i + 1][j] + 1, Integer.min(dp[i][j - 1] + 1, dp[i + 1][j - 1] + 2));
                    }
                }
            }
        }
        return 0;
    }
}