# 1. 初识Java

> 如果说C++是对C的一系列提升的话，那么它主要是在对C功能上的提升，代价是更晦涩的语法，那么Java就可以认为是对C++在语法难度和功能上的优化。所以，很多C++的语法格式和思想在Java中依然通用，比如条件判断语句、循环语句等在C、C++、Java中都是相同的

## JavaSE和JavaEE

**JavaSE**，即Java Standard edition，是Java的基础平台，用于开发桌面和简单的服务器程序，包含基本的语言特性与API

**JavaEE**，即Java  Enterprise Edition，是JavaSE的扩展版本，使用大型、分布式企业应用和Web应用

## 体系结构中立

**"Write once, Run Everywhere"**，源自于Java编译器和JVM的相互配合。Java编译器将`.java`文件中的代码转换为`.class`文件中的字节码（就是二进制代码），随后把它交给JVM运行。JVM维护了一套自己的指令集，与平台和硬件无关。Java程序在执行时，Java解释器会逐条将字节码文件中的指令转换为CPU的指令集

## 初识Java的main方法

```java
public class HelloWorld{
    public static void main(String[] args){
        System.out.println("hello world");
    }
}
```

* 类包含在源文件中，方法包含在类中，语句包含在方法中
* 一个源文件中只能有一个`public`类，`main`函数作为Java程序的执行入口，有着固定的声明方式，即：

```java
public static void main(String[] args){}
```

### Java程序的运行

我们的Java代码写在`.java`文件中，经由`javac`编译器生成`.class`字节码文件，它与平台无关，交给JVM使用；JVM会将字节码转换为平台能够理解的方式来执行

> JDK、JRE、JVM三者的关系？
>
> * JVM，Java Virtual Machine，负责将javac生成的平台无关的字节码文件转换为平台能够理解的方式执行
> * JRE，Java Runtime Environment，是Java的运行时环境，包括了JVM、Java基础类库
> * JDK，Java Development Kit，是Java开发工具包，包含了JRE、javac以及自带的调试工具等

### Java命名建议

1. 类名大驼峰（HelloWorld)
2. 方法名、变量名小驼峰（helloWorld)

# 2. 数据类型与变量

## 1. 字面常量

常量即程序运行期间，固定不变的量，主要包含字符串常量、整形常量、浮点数常量、字符常量、布尔常量、空常量null

## 2. 基本数据类型

包括**四类八种**。

**四类**：整型、浮点型、字符型、布尔型

**八种**：字节型、短整型、整型、长整型、单精度浮点数、双精度浮点数、字符型、布尔型

以下几点需要注意

* 整型无论在16位平台还是32位平台都是4个字节，长整型都是8个字节

* 整形、浮点型默认都是带有符号的
* 整形默认是int，浮点型默认是double
* 字符串属于引用类型

## 3. 变量

与C、C++声明、定义方式相同，但是需要注意的是，C++可以不给初始值使用默认值，Java在使用变量前必须初始化。

其次是关于**浮点数**，与C++一样，Integer相除默认是丢弃小数取整的，除非两个数中有一个浮点数（或者也可以对两个整型中的一个乘1.0），同时Java也和C++一样，采取有限空间表示无限的小数的方式，误差会存在的

还有就是关于**字符型变量**。在Java中不是采用的ASCII码来表示，而是UniCode来表示，因此字符型变量char是两个字节，也能够表示中文

最后是**布尔型变量boolean**，不同于C、C++，Java的布尔变量不能和整型之间相互转化，同时Java也没有对布尔型变量的大小作出规定

### 3.1 隐式、强制类型转换

**隐式类型转换**就是自己不用处理，编译时编译器会自动处理。数据范围小的转化为大的时自动进行（这样是安全的，不会丢失精度）

**强制类型转换**使用的方式和C、C++一样，在变量前加上`(type)`即可，不过需要注意的是，虽然这种方式可以在丢失精度的情况下将大的类型转化为小的类型（比如long->int），但是并不能将毫不相关的类型转换（比如char和boolean）

### 3.2 类型提升

#### 3.2.1 int and long

int会被提升为long，即不同类型运算时，结果会提升为更大的类型

#### 3.2.2 byte、short等

这些低于四个字节的类型，因为cpu一般以四个字节为单位从内存中取数据，所以会被提升为int

```java
public class DataType{
    public static void main(String[] args){
        byte b1 = 1, b2 = 2;
        byte b3 = (byte)(b1 + b2); //类型提升与强制类型转换
    }
}
```

### 3.3 字符串类型

Java用**String类**来表示字符串类型。

可以通过`String.valueOf(int)`、`int + ""`来完成整型转化为字符串，以及`Integer.parseInt(String)`来完成字符串转化成为整型

# 3. 标准输入输出

## 1. 标准输出

主要有以下三种方法

```java
System.out.println("hello world"); //打印一行文本，自动添加换行符
System.out.print("hello world"); //直接打印，不自动添加换行符
System.out.printf("hello %s", "world"); //print format
```

## 2. 标准输入

需要导入包`java.util.Scanneer`

```java
import java.util.Scanner;
public class InputOutput{
    public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
         String str = sc.nextLine();
         int val = sc.nextInt();
         float f = sc.nextFloat();
    }
}
```

## 3. 经典的猜数字游戏

需要导入包`java.util.Random`，初始化Random对象时直接指定范围

```java
package JavaSE.InputOutput;
import java.util.Scanner;
import java.util.Random;

public class NumGuess{
    public static void main(String[] args){
        System.out.println("please guess a number between 1~100, input nothing to exit");
        Random rand = new Random();
        int answer = rand.nextInt(100);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            int guess = sc.nextInt();
            if(guess < answer){
                System.out.println("smaller than the answer! Guess again?");
            }
            else if(guess > answer){
                System.out.println("bigger than the answer! Guess again?");
            }
            else{
                System.out.println("correct");
                break;
            }
        }
        sc.close();
    }
}
```

# 4. 方法

## 4.1什么是方法

其实可以对应到C、C++中的函数，由`修饰符 返回值 方法名([参数类型 参数名称...]){若干操作; return val;}`的形式组成。不同的是，Java中函数首先，返回值不是`void`的话，必须明确返回值，不能省略`return`语句；其次方法不能写在类的外面，也不能嵌套定义，没有方法声明一说

## 4.2 方法重载

其实和C++的规则一样：返回值不同不构成函数重载，要求参数的类型、个数至少有一个满足不同（即调用时就能根据参数的情况唯一确定一个调用对象），同时函数名相同

## 4.3 方法签名

为什么能够在同一个类中定义相同的方法呢？这是因为在`javac`生成的字节码文件中，采取`全路径+类型`的方式来表示方法，具体如何表示可以使用`javap`这一自带的反汇编工具查看

>在反汇编中，采取变量类型名称首字母大写来表示变量类型，例如int为I，double为D。但是Boolean为Z，数组以`[`开头，引用以`L`开头

# 5. 数组

## 5.1 初始化

Java中数组可以通过三种方式初始化：分别是动态初始化、静态初始化

```java
int[] arr1 = new int[10]; //动态初始化
int[] arr2 = new int[]{1, 2, 3}; //静态初始化
```

对于动态初始化，开辟的JVM的堆空间用对应类型的0来作为默认值（boolean为false），静态初始化会根据指定元素的数量自动确定大小

Java依然允许通过C++的方式初始化数组，但是并不建议。

``` java
int arr3[] = {1, 2, 3};
```

对于静态初始化， 也可以采用这种省略的方式

```java
int[] arr = {1, 2, 3};
```

同时，静态初始化和动态初始化也能够分离式的定义

``` java
int[] arr1;
arr1 = new int[10];
int[] arr2;
arr2 = new int[]{1, 2, 3};
```

但是省略写法的不可以

## 5.2 二维数组

```java
int[][] arr = new int[3][3]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
```

对于二维数组来说，行数不可以省略，列数可以省略。对于函数省略的数组，我们把它叫做**不规则的二维数组**，可以通过以下方式指定每一行的大小

```java
int[][] arr = new int[2][];
arr[0] = new int[3];
arr[1] = new int[4];
```

这样，我们就定义了一个两行，第一行三列，第二行四列的二维数组

## 5.3 关于引用

![jvm](C:\GitFiles\java-files\JavaSE\JVM.png)

上面是JVM的结构（虚拟机栈在有的JVM实现中是合并在一起的）

### 1. 方法区（Method Area）

- 属于**线程共享**区域
- 存储内容：
    - 已加载的**类信息**（类名、访问修饰符、字段、方法描述等）
    - **运行时常量池**（字面量、符号引用）
    - **静态变量（static）**
    - JIT 即时编译器编译后的**机器码**
- 字节码本身也在类加载后存放于此区域

### 2. 堆（Heap）

- **线程共享**，JVM 中最大的一块内存
- 存放**所有对象实例**与**数组**
- 垃圾回收（GC）主要管理的区域
- 字符串常量池在 JDK7 之后也从方法区移到了堆中

### 3. 虚拟机栈（JVM Stack）

- **线程私有**，生命周期与线程相同
- 每个方法执行时，会创建一个**栈帧（Stack Frame）**
- 栈帧中存放：
    - 局部变量表（基本数据类型、对象引用）
    - 操作数栈
    - 动态链接
    - 方法返回地址
- 常说的 “栈内存” 一般就是指虚拟机栈

### 4. 本地方法栈（Native Method Stack）

- 同样**线程私有**
- 作用：为 **native 方法**（C/C++ 实现的本地方法）服务
- 与虚拟机栈结构类似，只是服务对象不同
- 很多 JVM 实现会将**虚拟机栈 + 本地方法栈合并**

### 5. 程序计数器（Program Counter Register）

- **线程私有**
- 作用：记录当前线程**下一条需要执行的字节码指令地址**
- 线程切换、恢复执行时依赖它

对于像数组这样的应用变量，在虚拟机栈中，他存储的就只是堆中存储的实际数据的地址，因此在传参、返回时不需要通过专门的应用类型来减少拷贝，因为本身相当于传的就是一个指针

> 在Java虚拟机中，与C++程序的进程地址空间，近似有以下的对应关系：
>
> * **方法区 ≈ 数据段 + 代码段**：方法区主要存储类信息、字节码指令、编译后的机器指令、运行时常量池、字符串常量池，对应C++程序代码段存储编译好的机器码指令，以及代码段存放全局变量和静态变量的`.bss`/`.data`区与存放字符串常量和const常量的`.rodata`区
> * 虚拟机栈 + 本地方法栈 = 栈
> * 堆 = 堆

# 6.类和对象

## 6.1构造函数

* 和C++类似，构造函数编译器会生成一份默认的无参构造，并将成员变量都初始化为默认值；如果用户自己实现的话，要求函数名必须和类名相同，同时不能有返回值，void也不行。
* 允许在声明成员变量时指定默认值，进行就地初始化，类似C++的方法，并指定修饰符

## 6.2关于this引用

* 其实对应到了C++的this指针，用来指明访问的是那个对象的成员变量或者成员方法，谁调用，this引用就去引用谁。在对象内部访问本对象的成员变量、成员方法时，编译器都会加上这个隐式的this引用，也可以在成员方法中手动调用

* 构造函数中，也可以用this引用去执行其他方式的构造，只要不构成循环调用即可，例如

```java
public class Date{
    public int _year, _month, _day;
    public class Date(){
        this(0, 0, 0);
    }
    public class Date(int year, int month, int day){
        _year = year; _month = month; _day = day;
    }
}
```

## 6.3对象的打印

可以通过重写`toString`方法实现

```java
@Override
public String toString(){
    String str = "[" + _year + "-" + _month + "-" + _day + "]";
    return str;
}
```

## 6.4关于包：

**包**，是Java对类、接口的封装机制的体现，而且不仅仅体现在类似于C++的命名空间上，更体现在文件路径、文件加上，即上升到了文件的层次，使得项目整体结构更加清晰

其实使用包的思想和使用C++的命名空间思路上是一样的，可以指定包中的类，比如Date类，这就类似于C++通过域作用限定符访问某个命名空间中的方法、成员

```java
java.util.Date d = new java.util.Date();
System.out.println(d.getTime()); //后者毫秒级时间戳
```

第二种方法就是通过import导入，类似于C++的`using namespace std`这方式

```java
import java.util.Date;
import java.lang.*;
Date d = new Date();
```

不过需要注意的是，可能会发生冲突，比如`java.sql`中也有`Date`

同时，还可以通过import static的方式导入包中的静态成员、静态方法

```java
import static java.lang.Math.*;
System.out.println(sqrt(pow(2, 2.2) + pow(3, 3.3)));
```

### 6.4.1 static静态成员

1. 和C++一样，static静态成员变量、成员函数不属于某一个具体类，而是所有该类的实例化对象共有的一份，可以通过类型名访问，也可以通过对象访问。静态成员变量的生命周期与类相同，从类的加载开始，从类的卸载结束。
2. **关于静态成员的存储位置**，前面提到过，Java的方法区中存储着类的信息，而静态成员作为类的一种属性，也存储在这里

### 6.4.2 static静态成员的初始化

#### 就地构造

```java
public class Info{
    private String name;
    private static String school = "Fudan University";
}
```

类似于C++中声明时给缺省值是一样的，直接使用给定的值就地构造

#### 静态代码块初始化

```java
public class info{
    static String classId;
    static String school;
    String name;
    String nickname;
    {
        name = "wangjiale";    //构造代码块
        nickname = "human";
    }
    static {
        classId = "1";    //静态代码块
        school = "Fudan University";
    }
    public String toString(){
        {
            String info = name + nickname + classId + school;    //普通代码块
        }
        return info;
	}
}
```

代码块主要分为四种：**普通代码块、构造代码块、静态代码块、同步代码块**

**普通代码块**，就是写在方法里的代码块

**构造代码块**，用来初始化类的成员变量

**静态代码块**，用来初始化类的静态成员变量

**同步代码块**，主要用在多进程、多线程中，后面再介绍

关于静态代码块，如果有多个的话，那么编译器会把这些静态代码块合并在一起，在整个程序的运行过程中，一个类的静态代码块指挥被执行以此；构造代码块只会在实例化类时执行

## 6.5 继承

### 6.5.1 extends

使用`extends`关键字来表示继承关系，不需要像C++一样指定继承方式，因为Java类的每个方法、属性都带有访问限定作用的修饰符

### 6.5.2 关于访问限定符的作用

| 访问权限         | public             | protected          | default            | private            |
| ---------------- | ------------------ | ------------------ | ------------------ | ------------------ |
| 同一包中的同一类 | :white_check_mark: | :white_check_mark: | :white_check_mark: | :white_check_mark: |
| 同一包中的不同类 | :white_check_mark: | :white_check_mark: | :white_check_mark: |                    |
| 不同包中的子类   | :white_check_mark: | :white_check_mark: |                    |                    |
| 不同包中的非子类 | :white_check_mark: |                    |                    |                    |

对于类的方法和属性，只要没有加修饰符，都是default，在这个包内是公开的

### 6.5.3 super

使用`super`关键字来访问父类中同名的方法、变量，或者主动调用父类的构造方法完成父类的构造

**继承体系中的构造顺序**，和C++一样，都是采用先构造父类，再构造子类的方式。但是前面我们提到过，只要实现了自己的构造，编译器就不会再生成默认的无参构造。默认情况下，编译器为我们默认加上一个`super()`去调用父类的无参构造，在子类构造函数的第一句。但是如果编译器没有生成的话，就会报错。

所以这时我们需要手动调用父类的构造，比如以下情况

```java
class Base{
    int a;
    public Base(int a){
        this.a = a;
    }
}
class Derive extends Base{
    public Derive(){
        super(0);
    }
}
```

> `this`和`super`
>
> 1. `this`在非静态成员方法中，用来访问非静态的成员方法、变量；`super`在子类中，用来访问父类的成员方法、变量
> 2. `this`可以用来在类的一种构造中调用另一种构造；`super`在子类的构造的第一个语句中，可以去调用父类的构造
> 3. 编译器会自动在非静态的成员方法对非静态成员变量、方法的访问前加上this；无论父类的构造方法怎样，一定会去调用`super(...)`

### 6.5.4 继承关系

Java不允许多继承。C++允许多继承，虽然可以通过virtual虚继承的方式解决数据冗余和二义性的问题，但是这也有性能损耗，并且这种情况一般也可以通过设计避免。

Java直接杜绝了这种问题

### final

**作用于变量**，起到C++中`const`的作用，表示其不可被修改

**作用于类**，和C++中一样，表示不可被继承

### 6.5.5 多态

1. **多态**，是在继承体系中，子类对父类的方法在保证方法名、参数列表不变的情况下，完成子类自己的方法体实现的行为。想要体现出多态，需要通过基类的引用去调用重写完成的方法.
2. 对于多态的返回值，必须相同（除非能够构成父子关系）

> Java的处理思路其实和C++类似：重写要求函数名和参数列表相同，返回值不同的话，必须是父类返回父类的指针和引用，子类返回子类的指针和引用，这被称作协变

3. 关于访问权限问题：子类重写时，可以改变权限，但是可以缩小权限，比如从default到public
4. 父类的private方法、静态方法都不能被重写
5. 可以使用`@Override`来帮助检查是否完成重写，作用同C++的`override`

> 多态和重载的对比
>
> |            | 多态                                 | 重载                         |
> | ---------- | ------------------------------------ | ---------------------------- |
> | 参数列表   | 必须完全相同                         | 必须不同，参数的个数、类型等 |
> | 返回值     | 必须相同（除非构成父子关系）         | 都可以                       |
> | 访问限定符 | 可以改变，但是只能扩大允许访问的范围 | 扩大、缩小都可以             |
>
> 多态可以认为完成的是运行时的多态，是动态绑定，即运行时才能知道调用的是谁；重载完成的是编译时多态，是静态绑定，在编译时确定调用的方法

5. 关于**向上转型**和**向下转型**：两者可以认为是在自定义类这一级别上的类型转换规则。将子类对象赋值给父类，调用的都是父类的方法（或者子类重写的方法），这就是向上转型；将父类对象赋值给子类，调用的都是子类的方法，这就是向下转型

    但实际上，向下转换可能是不安全的。父类对象转换为子类对象，转换之后结果要求支持所有子类的访问接口，如果转换目标压根不满足父子关系或者父类对象使用其的其他父子关系向上转换来的，那么就会报错。

    Java为了提高安全性，引入了`instanceof`关键字来检查是否能够安全的向下转型
