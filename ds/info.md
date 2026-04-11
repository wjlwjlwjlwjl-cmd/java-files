# 1 泛型

## 1.1 基础数据类型与包装类对象

| 基础数据类型 | 包装类    |
| ------------ | --------- |
| char         | Character |
| short        | Short     |
| int          | Integer   |
| long         | Long      |
| float        | Float     |
| double       | Double    |
| boolean      | Boolean   |

## 1.2 包装类缓存池

先来看下面一段代码

```java
public class Cache{
    public static void main(String[] args){
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        System.out.println(a == b); //output: true;
        System.out.println(c == d;) //output: false;
    }
}
```

原因就是，Java为了提高效率，对各种基础数据类型都采取了包装类缓存池的策略，将一定范围的值的包装类都放到这个缓存池里面，各个引用这些包装类的引用类型都引用缓存池中的同一个对象。所以 a 和 b 引用的是同一个缓存池中的对象，而 c 和 d 引用的是 new 出来的两个不同的对象

综上我们也可以看出，== 比较的是引用对象是否是同一个，要比较值一定要使用 equals

>**基础数据类型包装类缓存池**
>
>| 包装类    | 缓存池范围     | 是否可变      |
>| --------- | -------------- | ------------- |
>| Character | 0~127          | 固定          |
>| Short     | -128~127       | 固定          |
>| Integer   | -128~127       | 可通过JVM调节 |
>| Long      | -128~127       | 固定          |
>| Boolean   | true and false | 固定          |
>
>Float 和 Double 没有包装类缓存池，Boolean 同种 == 比较一定是 true

## 1.3 基本的泛型

```java
class GenericClass<T>{
    public Object[] objs = new Object[10];
    public T getVal(int pos){
        return (T)objs[pos];
    }
    public void setVal(int pos, T val){
        objs[pos] = val;
    }
}
```

与 C++ 的泛型模板的目的有些不同。因为Java中所有类型都继承自 Object ，所以 Object 能接受所有类的传参。上面的 objs 数组，甚至可以储存不同类型的对象。

Java 的泛型，是为了指定当前的容器应该储存什么样的对象，并且交给编译器检查

Java 的泛型是编译期进行的，运行时会被擦除

### 1.3.1 泛型的使用

```java
GenericClass<String> gc = new GenericClass<>();
gc.setVal(0, "wangjiale");
gc.getVal(0);
```

在泛型类后指定类型实参。泛型只支持使用类，基础数据类型必须使用包装类

上面类型的实例化也可以省略类型，会进行自动的类型推导

### 1.3.2 泛型的上界

可以对泛型的类型进行约束，所有的类型实参都必须是上界的子类，比如下面

```java
class UpperClass<T extends Number>{}
class ComparableClass<T extends Comparable<T>>{}
UpperClass<Integer> uc = new UpperClass<>(); //Float Double Short都可以
ComparableClass<String> cc = new ComparableClass<>(); //支持比较的类才可以
```

### 1.3.2 泛型方法

```java
public static <E> void swap(E[] arr, int i, int j) {
    E tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}
```

泛型方法要求类型形参列表写在方法限定符后面（public、private、static等）

泛型方法的调用可以使用类型推导，也可以显示指定

```java
int a = 1, b = 2;
GenericMethod.swap(a, b); //使用类型推导
GenericMethod.<Integer>(a, b); //不适用类型推导
```

**小结：**

1. 泛型将数据类型参数化
2. `<T>`表示当前类型是泛型类
3. 泛型目前的优点：数据类型参数化，编译期间自动进行类型转换和检查