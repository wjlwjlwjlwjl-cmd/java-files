package JavaSE.DataType;

public class DataType {
    public static void main(String[] args){
        System.out.println("hello world");

        int a = 0; //与c++可以使用默认值初始化不同，变量使用前必须初始化
        System.out.println(a);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Float.MAX_VALUE);

        int b = 5, c = 3;
        System.out.println(b / c);
        double d = 5, e = 3;
        System.out.println(d / e);
        System.out.println(b / e);

        //字符类型
        char f = '帅';
        System.out.println(f); //java使用Unicode的方式保存字面量字符，因此是两个字节的大小

        //boolean
        boolean flag = true; //java中不允许boolean转化为int
        System.out.println(flag);

        int i = 1; double j = 1.0;
        j = i;//隐式类型转换
        i = (int)j;
        //f = (char)flag; //强制类型转换不一定成功

        //类型提升
        byte b1 = 'a', b2 = 'b';
        byte b3 = (byte)(b1 + b2);
        System.out.println(b3);

        //字符串类型
        String s1 = "hello world";
        int num = 10;
        //Integer -> 字符串
        String s2 = num + "";
        String s3 = String.valueOf(num);
        //字符串 -> Integer
        int num_cp = Integer.parseInt(s2);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(num_cp);
    }
}