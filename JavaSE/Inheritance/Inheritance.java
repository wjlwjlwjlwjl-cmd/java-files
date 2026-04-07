package JavaSE.Inheritance;

public class Inheritance{
    public static void main(String[] args){
        Derive d = new Derive();
    }
}

class Base{
    int a = 100;
    int b = 200;
    int c;
    public void methodA(){
        System.out.println("methodA in A");
    }
    public Base(int c){
        this.c = c;
    }
}

class Derive extends Base{
    int b = 300;
    int d = 400;
    public Derive(){
        super(10);
    }

    public void methodB(){
        methodA();
        System.out.println("methodB in B");
    }

    public void getVal(){
        System.out.println(super.b);
        System.out.println(a);
    }
}