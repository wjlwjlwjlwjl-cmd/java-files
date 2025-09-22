//源代码：Test.java->(javac) class文件（字节码文件，二进制）->(java执行)java虚拟机
//JDK->JRD(java runtime environment)->JVM(java virtual machine)
//
//注意事项：
// public class固定写法
//Test类名：大驼峰MaxNum，被public修饰的类名必须和文件名相同
//main写法public static void main(main函数的参数String[] args)

/*多行
  注释
 */

/** 
 * 文档注释
 * version 1
 * 入门第一个程序练习
*/

//Javac编译的时候中文按照GBK编码，如果编辑器的编码方式是UTF-8的话就会出现编码不一致而报错

public class Test{
    public static void main(String[] arg){//参数列表
        System.out.println(1.1);
        System.out.println("我是王家乐");
        System.out.println('.');
        System.out.println(true);
        System.out.println(false);
        int a = 10, b = 100;
        System.out.println(a + b);
        int c = 10;
        float f = 3;
        System.out.println(c / f);
        //int的包装类型为Integer
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        //long的包装类型为Long
        System.out.println(Long.MAX_VALUE);
        //short的包装类型为Short
        System.out.println(Short.MIN_VALUE);
        //字节型变量，也用来定义整型变量，如果给字符就打印ascii码值
        byte val = 'a';
        System.out.println(Byte.MAX_VALUE);
        System.out.println(val);

        //int / int = int
        System.out.println(4 / 3);
        //只要有浮点型那就进行小数的除法
        System.out.println(4 / 3.0);
        //double的包装类型是Double
        System.out.println(Double.MAX_VALUE);
        float val2 = 1f;//小数默认是double，如果想用浮点型的小数那么在小数后加上f即可
        System.out.println(val2);
        System.out.println(Float.MIN_VALUE);

        //Java的字符中可以存放整形。Java中采取unicode表示整形，占用两个字节，可以表示中文
        char ch1 = 1;
        char ch2 = 'A';
        char ch3 = '王';
        System.out.println(ch1);
        System.out.println(ch2);
        System.out.println(ch3);

        //布尔类型的变量(Java中其不能与int类型相互转换，其包装类型为Boolean)
        boolean val3 = true;
        System.out.println(val3);

        //类型转换
        //自动类型转换（隐式）
        int val4 = 10;
        double val5;
        val5 = val4;
        System.out.println(val5);//大的类型可以被小的类型经过转换后赋值，否则报错（double与float之间除外）
        //强制类型转换
        int val6 = (int)10.0;
        System.out.println(val6);
        byte val7 = 100;//自动
        byte val8 = (byte)300;
        System.out.println(val8);
        char val10 = 20000;
        System.out.println(val10);
        //boolean val9 = (boolean)100;不相干的类型不能互相转换
        //总结：java的隐式类型转换只适用于从小的类型赋值到大的类型，反之只能通过强制类型转换
    
        //类型提升：发生在不同数据进行相互运算时
        //两个规则：
        //1、小的类型会转换为大的类型
        //2、对于short byte char这些小于int类型的类型，因为计算机在内存中读取数据的基本单位是4kb，
        //所以会被先提升为int，再进行运算
        int val11 = 10;
        long val12 = 10;
        System.out.println(val11 + val12);
        int val13 = (int)(val11 + val12);
        System.out.println(val13);
        // byte val14 = 10;
        // byte val15 = 10;
        // byte val16 = val14 + val15;//整型提升

        //字符串类型：
        String s1 = "My name is";
        String s2 = " Wang Jiale";
        System.out.println(s1);
        System.out.println(s1 + s2);
        //int -> String
        int val17 = 100;
        //方法一：
        String str1 = val17 + "";
        System.out.println(str1);
        //方法二
        String str2 = String.valueOf(val17);
        System.out.println(str2);
        //String -> int
        String str3 = "1000";
        int val18 = Integer.parseInt(str3);
        System.out.println(val18);
    }
}
//javac -d 指定目录名 -author -version -encoding UTF-8-charset UTF-8 Test.java

//Test是类名，建议大驼峰（首字母大写）
//main是标识符，或者说叫做方法名建议小驼峰（首个单词小写其余大写）

//8GB相当于80多亿个字节