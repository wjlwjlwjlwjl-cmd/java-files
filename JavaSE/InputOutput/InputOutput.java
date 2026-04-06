package JavaSE.InputOutput;

import java.util.Scanner;

public class InputOutput {
    public static void main(String[] args){
        System.out.println("hello world");
        System.out.print("hello world\n");
        System.out.printf("hello %s", "world");

        //从键盘输入
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input your name: ");
        String name = sc.nextLine();
        System.out.println("Please input your salary: ");
        Float salary = sc.nextFloat();
        System.out.printf("your information: name-%s, salary-%f", name, salary);
        sc.close();
    }
}
