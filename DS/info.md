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

# 2. 协程

## 2.1 What's 协程

如果将线程比作一个工人的话，那么多线程就是让多个工人各自去干各自的活。但是当一位工人的工作暂时不能继续往下执行（比如等待IO事件），这位工人也就干等着，线程就被阻塞了，我们的并行计算能力就没有得到最大效率的发挥。

协程就是让我们实现一个用户级的“多线程环境”。多个函数中，每当一个函数需要等待时，就进入阻塞状态，返回对它调用的函数继续执行，当外界让他继续（resume）时，再中断刚才的执行，回到挂起前的地方继续执行。

如果继续用之前的比喻继续解释的话，就是：一个工人手头的工作需要等待时，就让工人去做其他的事情，而不是干等着。

除了对等待时间的利用更高之外，这种用户级别的“多线程”还不需要内核态 - 用户态的切换、上下文的处理等。

C++的协程是无栈协程，这意味着一个流程中，当去继续执行被挂起的函数时，就要从当前函数中暂停运行。在这个无栈协程的“栈”中，只需要保存局部状态（如恢复点）。

## 2.2 Promise

Promise是协程的心脏，决定了协程的操作句柄、异常处理、返回情况、变量存储，以及在开始、终止、生成数值是的挂起情况。

### 2.2.1 get_return_object

C++20的协程操作句柄 `coroutine_handle`，是通过 `Promise` 初始化的，一个 `Promise` 类型，需要在内部实现例子中的各种方法供给编译器自己去调用，而 `get_return_object`，产生的就是调用协程函数第一步返回的操作句柄

### 2.2.2 initial_suspend、final_suspend

决定协程函数在开始调用（执行第一个语句前）和结束（ `return_void` 或者 `return_value` ）之后是否需要挂起。如果挂起的话，只有外界通过对应的协程操作句柄 `resume` ，才能够继续往下执行。注意 `final_suspend` 需要实现为 `noexcept`

### 2.2.3 yeild_value

在协程函数调用 `co_yeild`时，用来完成对 `Promise` 内数据的操作逻辑。同样 `yeild_value` 可以决定后续是否挂起

### 2.2.4 return_void、return_value

用来确定协程函数的返回情况。`return_void` 和 `return_value` 只能实现一个，编译器会根据 `co_return` 的调用情况自主去调用相应实现

### 2.2.5 unhandled_exception

在协程函数中，如果抛出了异常并且没有 `catch` 的话，就会自动到 `unhandled_exception` 去执行异常处理逻辑，可以直接终止流程，也可以获取 `current_exception`，再 `rethrow_exception`

```cpp
struct promise_type {
	int current_value; 
	std::exception_ptr ex_ptr;
	int ret;
	Generator get_return_object() {
		return Generator{
		std::coroutine_handle<promise_type>::from_promise(*this) };
	}
	std::suspend_always initial_suspend() { return {}; }
	std::suspend_always final_suspend() noexcept { return {}; }
	std::suspend_always yield_value(int value) {
		current_value = value;
		return {};
	}
	//void return_void() {}
	void return_value(int val) {
		ret = val;
	}
	void unhandled_exception() {  
		ex_ptr = std::current_exception();
	}
};
```

## 2.2 协程句柄

### 2.2.1 What's 协程句柄

协程句柄允许外界操作一个协程帧（类似于栈，不过是一个协程的所有函数所共享的一个“栈”）。在析构时，可以通过类似RAII的方式销毁协程帧（如果 `final_suspend` 挂起的话）

### 2.2.2 from_promise()、promise()

`from_promise()`，通过 `Promise` ，去获取一个指向 `Promise` 对象的操作句柄；`promise()`，方便我们通过句柄去获取 `Promise` 对象及其内部值等

### 2.2.3 resume()

让处于挂起状态的协程从上次挂起的位置重新往下执行。

**对于未挂起状态的 `resume`，是未定义行为**

### 2.2.4 done()

检查一个协程是否已经执行完成，即是否已经调用 `co_return` 或者函数体是否已经执行完成

### 2.2.5 operator bool()

判断一个句柄是否是有效的句柄（协程句柄对于Promise对象的指向使用的是 `void*` 指针，所以可以用 `nullptr` 初始化

### 2.2.6 destroy()

销毁协程帧，不过对于 `final_suspend` 选择 `std::suspend_never` 的来说，不需要手动实现，并且二次销毁还是未定义行为

```c++
struct Generator {
	struct promise_type {
		//... 前面的实现省略
	};
	std::coroutine_handle<promise_type> handle;
	explicit Generator(std::coroutine_handle<promise_type> h) : handle(h) {}
	~Generator() {
		if (handle) handle.destroy();
	}
	int value() const {
		return handle.promise().current_value;
	}
	bool next() {
		if (!handle.done()) {
			handle.resume();
		}
		if (handle.promise().ex_ptr) {
			std::rethrow_exception(handle.promise().ex_ptr);
		}
		return !handle.done();
	}
};
```

