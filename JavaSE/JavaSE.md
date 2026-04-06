# 1. 初识Java

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

int会被提升为long

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