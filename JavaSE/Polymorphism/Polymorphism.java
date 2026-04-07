package JavaSE.Polymorphism;

public class Polymorphism{
    public static void main(){
        Animal ad = new Dog();
        Animal ac = new Cat();
        ad.eat();
        ac.eat();

        Cat cat = new Cat();
        Animal a_up = cat;
        a_up.eat();
        a_up.info();

        Animal a_down = new Dog();
        if(a_down instanceof Dog){
            a_down.eat();
        }
    }
}

class Animal{
    void eat(){
        System.out.println("eat food");
    }
    void info(){
        System.out.println("I'm animal");
    }
}

class Dog extends Animal{
    void eat(){
        System.out.println("eat dog's food");
    }
}

class Cat extends Animal{
    void eat(){
        System.out.println("eat cat's food");
    }
}