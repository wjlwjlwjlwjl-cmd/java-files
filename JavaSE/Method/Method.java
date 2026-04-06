package JavaSE.Method;

public class Method{
    public static void main(String[] args){
        System.out.println(add(1, 2));
        System.out.println(add(1.1, 2.2));
        System.out.println(fib(5));
    }

    public static int add(int a, int b){
        return a + b;
    }

    public static double add(double a, double b){
        return a + b;
    }

    public static int fib(int n){
        if(n == 1){
            return 1;
        }
        return n * fib(n - 1);
    }
}