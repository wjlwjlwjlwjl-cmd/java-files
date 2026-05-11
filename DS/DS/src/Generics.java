public class Generics {
    public static void main(String[] args){
        int a = 10;
        //转包：基本数据类型 -> 包装类对象
        Integer Ia = a;
        //拆包
        int b = Ia;
        //上述过程可以自动进行

        //泛型只能使用类，所以基础数据类型只能使用包装类
        GenericClass<String> gc = new GenericClass<>();
        gc.setVal(0, "wangjiale");
        gc.getVal(0);

        //可以通过extends设定上界，只有上界类型的子类才能作为类型参数
        UpperClass<Integer> uc = new UpperClass<>();
        ComparableClass<String> cc = new ComparableClass<>(); //只有实现了比较的才可以

        //泛型方法
        int m = 1, n = 2;
        GenericMethod gm = new GenericMethod();
        gm.swap(n, m);
        System.out.println(m);
        System.out.println(n);
        String s1 = "abc", s2 = "def";
        gm.<String>swap(s1, s2);
    }
}

class GenericClass<T>{
    public Object[] objs = new Object[10];
    public T getVal(int pos){
        return (T)objs[pos];
    }
    public void setVal(int pos, T val){
        objs[pos] = val;
    }
}

class UpperClass<T extends Number>{}

class ComparableClass<T extends Comparable<T>>{}

//泛型方法
class GenericMethod{
    public static<E> void swap(E e1, E e2){
        E tmp = e1;
        e1 = e2;
        e2 = tmp;
    }
}