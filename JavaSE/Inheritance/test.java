package JavaSE.Inheritance;

public class test extends BaseP{
    public static void main(String[] args){
        test t = new test();
        System.out.println(t.b);
        C c = new C();
        System.out.println(c.b);
        c.getD();
    }
}

class BaseP {
    protected int b = 0;
    final int d = 10;
}

class C extends BaseP{
    int c = 0;
    public void getD(){
        System.out.println(d);
    }
}