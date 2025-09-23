public class Test{
    public static void main(String[] args)
    {
        System.out.println(Add(1, 3));
        System.out.println(Sub(1, 3));
        System.out.println(Mul(1, 3));
        System.out.println(Div(1, 3));
        int arr[] = {1, 2};
        swap(arr);
        System.out.printf("%d %d", arr[0] , arr[1]);
        System.out.println("---------------------");
        byte a = 10, b = 20;
        double c = 10, d = 3;//如果将int类型赋值给双精度浮点型，在打印的时候会按照浮点数打印
        System.out.println(Add(a, b));
        System.out.println(Add(c, d));
        System.out.print(factor(3));
        Print(1234);
    }

    //Java中，方法，类似于C++中的函数，有以下组成
    //修饰符：public, private, protected, static...
    //返回值类型：不存在在返回值的过程中进行类型转换，返回的实体的类型必须和返回类型一致
    //在java中，方法必须写在类当中，也不存在什么方法声明，方法不能嵌套定义
    public static int Add(int x, int y){
        return x + y;
    }

    public static int Sub(int x, int y){
        return x - y;
    }

    public static int Mul(int x, int y){
        return x * y;
    }

    public static double Div(double x, double y){
        return x / y;
    }

    //方法参数的形参实参问题：
    //在Java中也存在形参实参的问题，而且在Java中形参和实参是两个独立的个体，形参只用来临时存放实参的值
    //不能和C++中一样存在引用的关系(或者说引用的使用方式不一样)
    public static void swap(int arr[]){
        arr[0] = arr[0] + arr[1];
        arr[1] = arr[0] - arr[1];
        arr[0] = arr[0] - arr[1];
    }

    //Java中的函数重载用法与C++中的相同，不取决与返回值类型，而参数列表需要不同
    //（参数的类型、个数、次序）
    public static double Add(double x, double y){
        return x + y;
    }
    public static int Add(byte x, byte y){
        return x + y;
    }

    //在java中，同一个作用域中不能定义两个相同名称的变量
    //而在类中可以定义方法名相同的方法，因为方法的签名是经过编译器修改的
    //=方法全路径名+参数列表+返回值
    //例如extend01/Test.Add:(II)I

    //递归
    public static int factor(int n){
        if(n == 1){
            return 1;
        }
        else{
            return n * factor(n - 1);
        }
    }
    //按照顺序打印一个数字的每一位
    public static void Print(int x){
        if(x < 10){
            System.out.printf("%d ",x);
        }
        else{
            System.out.printf("%d ", x % 10);
            Print(x / 10);
        }
    }
}