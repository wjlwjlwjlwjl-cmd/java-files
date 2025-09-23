
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        //数组的创建与初始化
        int[] arr1;//动态初始化(通过这种初始化方法，未初始化的都可以被初始化为0)
        arr1 = new int[]{1, 2, 3, 4};
        double[] arr2 = {1, 2, 3, 4, 5, 6};//静态初始化
        String[] arr3 = new String[]{"wang", "jia", "le"};//静态初始化
        for (int i = 0; i < 3; i++) {
            System.out.println(arr1[i]);
            System.out.println(arr2[i]);
            System.out.println(arr3[i]);
        }
        Function(arr1);
        System.out.println(arr1[0]);
        testArray();
    }

    //数组初始化的默认值——所以不指定值的数组定义叫做动态初始化，还可以后期再进行指定
    //整型类型的初始化值为0
    //float 0.0f
    //double 0.0
    //char /u0000
    //boolean false
    //--------------------------------------------------
    //                                                  |
    //  方法区          虚拟机栈        本地方法栈        |
    //                                                  |
    //                                                  |
    //  堆                  程序计数器                   |
    //                                                  |
    //---------------------------------------------------
    //对于基本类型变量（C中的内置类型变量）直接存放在虚拟机栈当中
    //而引用类型变量（如数组）在虚拟机栈中只存放一个指向堆的地址，实际上的内容只存放在堆中
    //null是java中的空引用，不指向对象的引用
    //传参
    public static void Function(int[] arr) {
        arr[0] = 10;
    }

    //作为函数的返回值
    public static int[] Fib(int n) {
        if (n < 0) {
            return null;
        }
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] - arr[i - 2];
        }
        return arr;
    }

    //数组转字符串
    public static void testArray() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        String newStr = Arrays.toString(arr);
        System.out.println(newStr);
    }
}
