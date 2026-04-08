package JavaSE.Interface;
import java.util.Comparator;

public class Interface {
    public static void main(){
        USB usb;
        Computer com = new Computer();
        com.useUSB(new Mouse());
        com.useUSB(new KeyBoard());
        System.out.println("*********************");
        Duck duck = new Duck("duck");
        duck.run();
        duck.fly();
        duck.swim();
        System.out.println("*********************");
        Student stu1 = new Student("zhangsan", 98, com);
        Student stu2 = new Student("lisi", 99, com);
        System.out.println(stu1.compareTo(stu2));
        System.out.println("*********************");
        NameComparator nc = new NameComparator();
        System.out.println(nc.compare(stu1, stu2));
        System.out.println("*********************");
        Student stuClone = stu1.clone();
        System.out.println(stuClone.ps);
        stu1.ps = null;
        System.out.println(stuClone.ps);
        System.out.println("*********************");
        Greeting greet = new Greeting(){
            String name = "zhangsan";
            @Override public void Greet(){
                System.out.println("hello");
            }
        };
        greet.Greet();
        System.out.println("*********************");
        Student s3 = new Student("lisi", 111, com);
        Student s4 = new Student("lisi", 111, com);
        System.out.println(s3.equals(s4));
    }
}

//接口中的所有方法，默认都是抽象共有方法，使用的类必须实现，public abstract建议不写默认
//接口是一种引用类型，但是不能直接new
interface USB{
    void openDevice();
    void closeDevice();
    String brand = "unknown"; //接口中可以有成员变量，但是默认会加上public static final
}
class Mouse implements USB{
    @Override
    public void openDevice(){
        System.out.println("Mouse open");
    }
    @Override
    public void closeDevice(){
        System.out.println("Mouse close");
    }
}

class KeyBoard implements USB{
    @Override
    public void openDevice(){
        System.out.println("KeyBoard open");
    }
    @Override
    public void closeDevice(){
        System.out.println("KeyBoard close");
    }
}

class Computer{
    String name = "computer";
    public void useUSB(USB u){
        if(u instanceof KeyBoard){
            System.out.println("KeyBoard detected");
        }
        else if(u instanceof Mouse){
            System.out.println("Mouse detected");
        }
        u.openDevice();
        u.closeDevice();
    }
    static class Info{
        String brand;
    }
}

//Java中，一个类只能有一个父类，但是可以实现多种接口
//继承表示的关系是is-a，组合表示的关系是has-a，而接口表示的是具有某种属性
class Animal{
    String name = "animal";
    public Animal(String name){
        this.name = name;
    }
}

interface IRunning{
    void run();
}
interface IFlying{
    void fly();
    String name();
}
interface ISwimming{
    void swim();
    String name();
}
class Duck extends Animal implements IRunning, IFlying, ISwimming{
    public Duck(String name){
        super(name);
    }
    @Override public String name(){
        System.out.println(name);
        return name;
    }
    @Override public void run(){
        System.out.println("duck run");
    }
    @Override public void fly(){
        System.out.println("duck fly");
    }
    @Override public void swim(){
        System.out.println("duck swim");
    }
}

//Java虽然不允许类之间的多继承关系，但是允许接口之间存在多继承，其实就是因为C++的多继承对于变量会造成数据冗余和二义性
interface IAmphibious extends IRunning, ISwimming{
    void move();
}

//接口的使用：大小比较
class Student implements Comparable<Student>, Cloneable{
    public String name;
    public int score;
    public Computer ps;
    public Student(String name, int score, Computer ps) {
        this.name = name;
        this.score = score;
        this.ps = ps;
    }
    public int compareTo(Student t){
        if(this.score < t.score){
            return -1;
        }
        else if(this.score > t.score){
            return 1;
        }
        else{
            return 0;
        }
    }
    @Override public Student clone(){
        Student stu = null;
        try{
            stu = (Student)super.clone();
        }
        catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return stu;
    }
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else if(obj == this){
            return false;
        }
        else if(!(obj instanceof Student)){
            return false;
        }
        Student s = (Student)obj;
        return s.name.equals(this.name) && s.score == this.score;
    }
}
class ScoreComparator implements Comparator<Student>{
    @Override public int compare(Student s1, Student s2){
        if(s1.score < s2.score){
            return -1;
        }
        else if(s1.score > s2.score){
            return 1;
        }
        else{
            return 0;
        }
    }
}

class NameComparator implements Comparator<Student>{
    public int compare(Student s1, Student s2){
        return s1.compareTo(s2);
    }
}

interface Greeting{
    void Greet();
}