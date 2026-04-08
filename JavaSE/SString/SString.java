package JavaSE.SString;

public class SString {
    public static void main(String[] args){
        //String是引用类型
        //对于直接给定字符串的初始化，在字符串常量区只会存一份
        //对于new方式的初始化，每次都会在堆中开辟新的String对象，但是字符串常量池中的字符串数据只有一份，这时引用
        //引用的就是这个堆中的新建对象，而不是常量池中的字符串本身
        String s1 = new String("hello");
        String s2 = new String("hello");
        String s3 = s1;
        String s5 = "Hello";
        String s6 = "Hello";
        System.out.println(s5 == s6);

        //==比较的是，两个引用是否是引用的是否是同一个对象或者字符串
        System.out.println(s1 == s2); //false
        System.out.println(s1 == s3); //true

        //equals和compareTo、compareToIgnoreCase，比较的是字典序，但在返回值上有所差别
        System.out.println(s1.equals(s2)); //boolean
        System.out.println(s1.compareTo(s2)); //int，字典序差值，或者长度差值
        String s4 = "Hello";
        System.out.println(s1.compareToIgnoreCase(s4));

        //字符串和数字的转换
        String s7 = 1 + "";
        String s8 = String.valueOf(10);
        int a = Integer.parseInt(s7);

        //大小写转换
        String s9 = "wangjiale";
        String s10 = s9.toUpperCase();
        String s11 = s10.toLowerCase();
        System.out.println(s10 + s11);

        //格式化
        String s12 = String.format("%d-%d-%d", 1, 2, 3);

        //替换
        String s13 = s1.replaceAll("l", "_");
        String s14 = s13.replaceFirst("l", "-");
        System.out.println(s13 + s14);

        //字符串拆分
        String s15 = "Hello World! Hello Java";
        String[] strs = s15.split(" ", 3);
        for(String s: strs){
            System.out.println(s);
        }

        //字串
        String s16 = s15.substring(0, 5);
        System.out.println(s16);

        //intern
        String s17 = "Java";
        String s18 = new String("Java").intern();
        System.out.println(s17 == s18);

        //字符串修改——StringBuffer StringBuilder，StringBuffer是线程安全的
        long begin = System.currentTimeMillis();
        String S1 = "Hello Java";
        for(int i = 0; i < 100000; i++){
            S1 += i;
        }
        long end = System.currentTimeMillis();
        System.out.printf("String: %d\n", end - begin);

        begin = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer("Hello Java");
        for(int i = 0; i < 100000; i++){
            sb.append(i);
        }
        end = System.currentTimeMillis();
        System.out.printf("StringBuffer: %d\n", end - begin);

        begin = System.currentTimeMillis();
        StringBuilder sbd = new StringBuilder("Hello Java");
        for(int i = 0; i < 100000; i++){
            sbd.append(i);
        }
        end = System.currentTimeMillis();
        System.out.printf("StringBuilder: %d\n", end - begin);
    }
}
