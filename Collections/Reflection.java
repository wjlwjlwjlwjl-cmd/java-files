package Collections;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

class B{
    private int val1;
    public int val2;
    public B(int val1, int val2){
        this.val1 = val1;
        this.val2 = val2;
    }
    private B(){
        this.val1 = 1;
        this.val2 = 2;
    }
    private void getA(){
        System.out.println(val1);
    }
    @Override
    public String toString(){
        return "----" + val1 + ":" + val2;
    }
}

public class Reflection{
    public static void main(String[] args){
        getConstructor();
        getPrivateConstructor();
        getPrivateMethod();
        getPrivateField();
    }

    public static void getConstructor(){
        try{
            Class<?> classB = Class.forName("Collections.B");
            Constructor<?> constructor = classB.getConstructor(int.class, int.class);
            B b = (B)constructor.newInstance(1, 2);
            System.out.println("construct B publicly" + b);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void getPrivateConstructor(){
        try{
            Class<?> classB = Class.forName("Collections.B");
            Constructor<?> constructor = classB.getDeclaredConstructor();
            constructor.setAccessible(true);
            B b = (B)constructor.newInstance();
            System.out.println("construct B privately" + b);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void getPrivateMethod(){
        try{
            Class<?> classB = Class.forName("Collections.B");
            Constructor<?> constructor = classB.getDeclaredConstructor(int.class, int.class);
            B b = (B)constructor.newInstance(1, 2);
            Method privateMethod = classB.getDeclaredMethod("getA");
            privateMethod.setAccessible(true);
            System.out.println("get private method: " + privateMethod.getName());
            privateMethod.invoke(b);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void getPrivateField(){
        try{
            Class<?> classB = Class.forName("Collections.B");
            Constructor<?> constructor = classB.getDeclaredConstructor(int.class, int.class);
            B b = (B)constructor.newInstance(1, 2);
            Field privateField = classB.getDeclaredField("val1");
            privateField.setAccessible(true);
            int val1 = (int)privateField.get(b);
            System.out.println("get private field: " + val1);
            privateField.set(b, 5);
            val1 = (int)privateField.get(b);
            System.out.println("modify private field: " + val1);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}

//docker
//cmake
//