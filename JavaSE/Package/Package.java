package JavaSE.Package;

import java.util.Date; //直接导入
//import java.sql.*;
import static java.lang.Math.*; //导入包中的静态成员和方法

class Import{
    public static void test(){
        Date d1 = new java.util.Date();
        System.out.println(d1.getTime());
        System.out.println(sqrt(pow(3, 1.1) + pow(2, 1.2)));
    }
}

class Computer{
    private String _brand;
    private int _innerStore;

    public void set(String brand, int innerStore){
        _brand = brand;
        _innerStore = innerStore;
    }

    public String getBrand(){
        return _brand;
    }

    public int getInnerStore(){
        return _innerStore;
    }
}

class Class{
    public String name;
    static String teacher;
    static int roomNumber; //生命周期伴随类，存储在方法区中，可以通过类名访问，也可以通过对象访问
    {
        name = "zhangsan";
    }
    static{
        teacher = "wangwu";
        roomNumber = 101;
    }
}

public class Package{
    public static void main(String[] args){
        Computer com = new Computer();
        com.set("Lenevo", 32);
        System.out.println(com.getBrand());
        System.out.println(com.getInnerStore());
    }
}