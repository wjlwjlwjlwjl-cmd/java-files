package JavaSE.Inheritance;

public class test extends Base{
    public static void main(String[] args){
        test t = new test();
        System.out.println(t.b);
        C c = new C();
        System.out.println(c.b);
        c.getD();
    }
}

class Base {
    protected int b = 0;
    final int d = 10;
}

class C extends Base{
    int c = 0;
    public void getD(){
        System.out.println(d);
    }
}