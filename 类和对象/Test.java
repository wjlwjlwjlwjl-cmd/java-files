
public class Test {

    public static void main(String[] args) {
        //类的实例化
        Car car1 = new Car("xiaomi", "200000￥", "red", "SV8", "500km");//new关键字用于创建一个对象实例
        car1.showInfo();
        Car car2 = new Car();
        car2 = car1;
        Car car3 = car1;
        car1.showInfo();
        car2.showInfo();
        car3.showInfo();
    }
}

//public修饰的类必须要和文件名相同
//一般一个文件当中只定义一个类
//main方法所在的类，一般要通过public修饰
class Car {

    public String _brand = "Benz";
    public String _price = "1000$";
    public String _color = "Black";
    public String _engine = "V8";
    public String _distance = "300km";

    //在Java中，如果自己不定义构造函数，编译器会默认生成一份，这份默认构造一定是无参的
    public Car() {
    }

    public Car(String brand, String price, String color, String engine, String distance) {
        _brand = brand;
        _price = price;
        _color = color;
        _engine = engine;
        _distance = distance;
        System.out.print("Car(String brand, String price, String color, String engine, String distance)\n");
    }

    public Car(Car other_car) {
        _brand = other_car._brand;
        _price = other_car._price;
        _engine = other_car._engine;
        _color = other_car._color;
        _distance = other_car._distance;
        System.out.print("Car(Car other_car)\n");
    }

    public void showInfo() {
        System.out.printf("brand: %s\n", _brand);
        System.out.printf("price: %s\n", _price);
        System.out.printf("engine: %s\n", _engine);
        System.out.printf("color: %s\n", _color);
        System.out.printf("distance: %s\n", _distance);
    }
}

//this引用：和C++的this指针是一样的，只不过Java不存在指针这个概念
class Date {

    public int year;
    public int month;
    public int day;

    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void printDate(
         Date this){
        System.out.printf("%d - %d - %d", this.year, this.month, this.day);
    }
}

class Person {

    public String _name;
    public int _age;

    //构造方法中可以通过this来调用其他方法来简化代码，但是this语句必须在第一行，而且不能形成环状结构
    public Person() {
        this("王家乐", 18);
    }

    public Person(String name, int age) {
        _name = name;
        _age = age;
    }
}
